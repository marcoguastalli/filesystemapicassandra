package net.marco27.api.filesystemapi.service;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.executor.PrintFileSystemExecutor;
import net.marco27.api.filesystemapi.executor.thread.CreateFileStructureThread;
import net.marco27.api.filesystemapi.executor.thread.PrintFileSystemThread;

@Service
public class FileSystemApiServiceImpl implements FileSystemApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemApiServiceImpl.class);

    private PrintFileSystemExecutor printFileSystemExecutor;

    @Autowired
    public FileSystemApiServiceImpl(@Autowired final PrintFileSystemExecutor printFileSystemExecutor) {
        this.printFileSystemExecutor = printFileSystemExecutor;
    }

    @Override
    public PathFileToPrint printPathToFile(final String pathToPrint, final String fileToPrint) {
        try {
            printFileSystemExecutor.execute(new PrintFileSystemThread(pathToPrint, fileToPrint));
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error(String.format("Error printPathToFile with pathToPrint: %s and fileToPrint: %s", pathToPrint, fileToPrint), e);
        }
        return new PathFileToPrint.Builder(pathToPrint, fileToPrint).build();
    }

    @Override
    public FileStructure createFileStructure(final String path) {
        final CreateFileStructureThread createFileStructureThread = new CreateFileStructureThread(path);
        try {
            return printFileSystemExecutor.execute(createFileStructureThread);
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error(String.format("Error createFileStructure with path: %s", path), e);
        }
        return null;
    }

}
