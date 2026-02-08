package com.design.portfolio.exceptions;

import com.design.portfolio.exceptions.abs.GeneralException;

public class ImagesNotFoundException extends GeneralException {
    public ImagesNotFoundException(String message) {
        super(message);
    }
}
