package com.gabrielliondas.workshopmongo.exception;

import java.io.Serial;

public class NonValidObjectException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NonValidObjectException(String msg) {
        super(msg);
    }
}
