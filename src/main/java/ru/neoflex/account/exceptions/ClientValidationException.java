package ru.neoflex.account.exceptions;

public class ClientValidationException extends RuntimeException{
    public ClientValidationException(String message) {
        super(message);
    }
}
