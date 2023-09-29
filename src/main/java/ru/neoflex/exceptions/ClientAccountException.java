package ru.neoflex.exceptions;

public class ClientAccountException extends RuntimeException{
    public ClientAccountException(String message) {
        super(message);
    }
}
