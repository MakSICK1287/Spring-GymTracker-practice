package org.example.gymtrackerspring.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email){super("пользователь с данным email "+email+" уже существует");}
}
