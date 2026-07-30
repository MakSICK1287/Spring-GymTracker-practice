package org.example.gymtrackerspring.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){super("пользователь с username "+username+" уже существует");}
}
