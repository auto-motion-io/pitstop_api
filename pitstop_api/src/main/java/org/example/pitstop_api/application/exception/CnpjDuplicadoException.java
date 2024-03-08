package org.example.pitstop_api.application.exception;

public class CnpjDuplicadoException extends RuntimeException{
    public CnpjDuplicadoException(String message) {
        super(message);
    }
}
