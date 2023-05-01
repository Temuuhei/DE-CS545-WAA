package com.fy.productservice.controller;

public class ProductError {

    private String errorMessage;

    public ProductError (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage () {
        return this.errorMessage;
    }
}
