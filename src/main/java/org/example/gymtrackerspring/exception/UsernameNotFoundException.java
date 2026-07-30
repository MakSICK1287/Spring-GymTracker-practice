package org.example.gymtrackerspring.exception;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username){super("пользователя с username "+username+" не существует");}
}
