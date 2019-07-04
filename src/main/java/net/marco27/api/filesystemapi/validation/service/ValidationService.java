package net.marco27.api.filesystemapi.validation.service;

import net.marco27.api.base.validation.service.BaseValidationService;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;

public interface ValidationService extends BaseValidationService {

    ValidationResult validateInput(PathFileToPrint pathFileToPrint);

    boolean validatePathToPrint(String pathToPrint);

    boolean validateFileToPrint(String fileToPrint);

}
