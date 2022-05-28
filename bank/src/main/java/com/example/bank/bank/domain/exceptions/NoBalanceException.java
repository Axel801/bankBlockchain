package com.example.bank.bank.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NoBalanceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private long idUser;

    public NoBalanceException(long idUser) {

        super(String.format("%s not have founds", idUser));

        this.idUser = idUser;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

}
