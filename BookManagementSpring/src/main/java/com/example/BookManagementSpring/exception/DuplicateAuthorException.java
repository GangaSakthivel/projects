package com.example.BookManagementSpring.exception;

public class DuplicateAuthorException extends RuntimeException{
    public DuplicateAuthorException(String message){
        super(message);
    }
}
