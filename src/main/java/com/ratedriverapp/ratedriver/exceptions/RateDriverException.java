package com.ratedriverapp.ratedriver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class RateDriverException extends RuntimeException {
    public RateDriverException() {
    }

    public RateDriverException(String message) {
        super(message);
    }

    public RateDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateDriverException(Throwable cause) {
        super(cause);
    }

    public RateDriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
