package com.example.BookManagementSpring.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException (String message){
        super(message);
    }
}
