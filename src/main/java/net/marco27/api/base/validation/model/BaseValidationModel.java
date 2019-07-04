package net.marco27.api.base.validation.model;

import java.io.Serializable;

public class BaseValidationModel<T> implements Serializable {

    private T objToValidate;

    public BaseValidationModel(final T objToValidate) {
        this.objToValidate = objToValidate;
    }

    public T getObjToValidate() {
        return objToValidate;
    }
}
