package net.marco27.api.base.validation.service;

import net.marco27.api.base.validation.model.BaseValidationModel;
import net.marco27.api.base.validation.model.BaseValidationResult;

public interface BaseValidationService {

    BaseValidationResult validate(BaseValidationModel baseValidationModel);

}
