package com.example.jdbc.demo.helper;

import java.util.List;

public class ErrorResponse extends ErrorDetails{

    private List<ErrorDetails> errors;

    public List<ErrorDetails> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetails> errors) {
        this.errors = errors;
    }
}