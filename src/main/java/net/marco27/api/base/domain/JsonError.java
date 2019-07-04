package net.marco27.api.base.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonError implements Serializable {

    private String errorMessage = "ERROR!";

    public JsonError addErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
