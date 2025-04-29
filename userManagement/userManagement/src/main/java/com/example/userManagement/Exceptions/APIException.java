package com.example.userManagement.Exceptions;

public class APIException extends RuntimeException{
    public APIException (String message){
        super(message);
    }
}
