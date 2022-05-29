package com.example.bank.bank.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class BlockchainException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public BlockchainException(String message) {

        super(message);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
