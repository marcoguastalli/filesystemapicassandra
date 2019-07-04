package net.marco27.api.base.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonSuccess implements Serializable {

    private static final String SUCCESS_OK = "SUCCESS";
    private String success;

    public JsonSuccess() {
        this.success = SUCCESS_OK;
    }

    public JsonSuccess(final String success) {
        this.success = success;
    }

}
