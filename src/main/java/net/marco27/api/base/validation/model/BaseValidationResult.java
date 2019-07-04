package net.marco27.api.base.validation.model;

import java.io.Serializable;

import lombok.Setter;

@Setter
public class BaseValidationResult implements Serializable {

    private boolean isValid;

    public BaseValidationResult(final boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }
}
