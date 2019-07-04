package net.marco27.api.base.validation.service;

import org.springframework.stereotype.Service;

import net.marco27.api.base.validation.model.BaseValidationModel;
import net.marco27.api.base.validation.model.BaseValidationResult;

@Service
public class BaseValidationServiceImpl implements BaseValidationService {

    @Override
    public BaseValidationResult validate(final BaseValidationModel baseValidationModel) {
        if (baseValidationModel.getObjToValidate() == null) {
            return new BaseValidationResult(Boolean.FALSE);
        }
        return new BaseValidationResult(Boolean.TRUE);
    }

}
