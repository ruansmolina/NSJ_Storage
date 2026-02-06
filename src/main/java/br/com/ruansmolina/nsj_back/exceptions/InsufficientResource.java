package br.com.ruansmolina.nsj_back.exceptions;

public class InsufficientResource extends RuntimeException {
    public InsufficientResource(String message) {
        super(message);
    }
}
