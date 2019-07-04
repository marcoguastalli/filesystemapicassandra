package net.marco27.api.filesystemapi.validation.service;

import static net.marco27.api.base.ApiConstants.SLASH;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.marco27.api.base.validation.service.BaseValidationServiceImpl;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;

@Service
public class ValidationServiceImpl extends BaseValidationServiceImpl implements ValidationService {

    @Override
    public ValidationResult validateInput(final PathFileToPrint pathFileToPrint) {
        if (validatePathToPrint(pathFileToPrint.getPathToPrint()) && validateFileToPrint(pathFileToPrint.getFileToPrint())) {
            return new ValidationResult(Boolean.TRUE, StringUtils.EMPTY, HttpStatus.OK);
        } else {
            return new ValidationResult(Boolean.FALSE, String.format("Invalid input: %s", pathFileToPrint), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public boolean validatePathToPrint(final String pathToPrint) {
        return !StringUtils.isEmpty(pathToPrint) && StringUtils.startsWith(pathToPrint, SLASH);
    }

    @Override
    public boolean validateFileToPrint(final String fileToPrint) {
        return !StringUtils.isEmpty(fileToPrint) && StringUtils.startsWith(fileToPrint, SLASH);
    }

}
