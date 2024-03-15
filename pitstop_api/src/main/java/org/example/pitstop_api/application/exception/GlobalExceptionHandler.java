package org.example.pitstop_api.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DadoUnicoDuplicadoException.class)
    public ResponseEntity<String> handleDadoUnicoDuplicadoException(DadoUnicoDuplicadoException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<String> handleSenhaIncorretaException(SenhaIncorretaException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException ex) {
        return ResponseEntity.status(500).body("Operação não concluída SQLException: \n" +
                ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400).body("Falha nas validações dos valores recebidos: \n" +
                ex.getMessage());
    }

    @ExceptionHandler(SenhaNulaOuVaziaException.class)
    public ResponseEntity<String> handleSenhaNulaOuVaziaException(SenhaNulaOuVaziaException ex) {
        return ResponseEntity.status(403).body("Usuário foi encontrado porém proibido de entrar!\n" +
                ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
    }
}