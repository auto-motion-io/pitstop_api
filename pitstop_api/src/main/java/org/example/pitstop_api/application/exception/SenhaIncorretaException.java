package org.example.pitstop_api.application.exception;

public class SenhaIncorretaException extends RuntimeException{
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
