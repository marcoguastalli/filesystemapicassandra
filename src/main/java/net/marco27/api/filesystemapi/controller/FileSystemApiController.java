package net.marco27.api.filesystemapi.controller;

import net.marco27.api.base.domain.JsonError;
import net.marco27.api.base.domain.JsonSuccess;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.repository.FileStructureCassandraRepository;
import net.marco27.api.filesystemapi.service.FileSystemApiService;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;
import net.marco27.api.filesystemapi.validation.service.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static net.marco27.api.base.ApiConstants.SLASH;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * The main use case for the API is to read the filesystem
 */
@RestController
@RequestMapping
public class FileSystemApiController {

    @Autowired
    private FileSystemApiService fileSystemApiService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FileStructureCassandraRepository fileStructureCassandraRepository;

    @GetMapping("/printPathToFile/{pathToPrint}/{fileToPrint}")
    public ResponseEntity<PathFileToPrint> printPathToFile(@Valid @PathVariable final String pathToPrint,
                                                           @Valid @PathVariable final String fileToPrint) {
        final PathFileToPrint pathFileToPrint = new PathFileToPrint.Builder(pathToPrint, fileToPrint).build();
        final ValidationResult validationResult = validationService.validateInput(pathFileToPrint);
        if (validationResult.isValid()) {
            final PathFileToPrint result = fileSystemApiService.printPathToFile(pathFileToPrint.getPathToPrint(),
                    pathFileToPrint.getFileToPrint());
            return new ResponseEntity<>(result, CREATED);
        } else {
            return new ResponseEntity(new JsonError().addErrorMessage(validationResult.getErrorMessage()),
                    validationResult.getHttpStatus());
        }
    }

    /**
     * The input path parameter from a request cannot start with SLASH, but absolute paths are used
     *
     * @param path to validate
     * @return a valid path
     */
    @Valid
    private String validatePath(@PathVariable @Valid final String path) {
        return StringUtils.startsWith(path, SLASH) ? path : SLASH.concat(path);
    }

    @GetMapping("/findFileStructureById/{path}")
    public ResponseEntity<FileStructure> getFileStructure(@Valid @PathVariable final String path) {
        final Optional<FileStructure> result = fileStructureCassandraRepository.findById(validatePath(path));
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/findFileStructureByPath/{path}")
    public ResponseEntity<FileStructure> findFileStructure(@Valid @PathVariable final String path) {
        FileStructure result = fileStructureCassandraRepository.findByPath(validatePath(path));
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteFileStructure/{path}")
    public ResponseEntity<JsonSuccess> deleteFileStructure(@Valid @PathVariable final String path) {
        final Optional<FileStructure> result = fileStructureCassandraRepository.findById(validatePath(path));
        if (result.isPresent()) {
            fileStructureCassandraRepository.delete(result.get());
            return ResponseEntity.ok(new JsonSuccess());
        }
        return ResponseEntity.ok(new JsonSuccess(String.format("path not found %s", path)));
    }

    @PostMapping("/saveFileStructure/{path}")
    public ResponseEntity<FileStructure> storeFileStructure(@Valid @PathVariable final String path) {
        final String validPath = validatePath(path);
        FileStructure fileStructure = fileSystemApiService.createFileStructure(validPath);
        if (fileStructure != null) {
            fileStructure = fileStructureCassandraRepository.save(fileStructure);
        }
        return ResponseEntity.ok(fileStructure);
    }

}
