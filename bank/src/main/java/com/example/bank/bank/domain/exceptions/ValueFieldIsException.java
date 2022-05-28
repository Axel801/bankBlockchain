package com.example.bank.bank.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class ValueFieldIsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String resourceName;

    private String fieldName;

    private String valueName;

    public ValueFieldIsException(String resourceName, String fieldName, String valueName) {

        super(String.format("%s %s is '%s'", resourceName, fieldName, valueName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.valueName = valueName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
