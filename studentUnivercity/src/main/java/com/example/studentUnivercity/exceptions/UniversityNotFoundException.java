package com.example.studentUnivercity.exceptions;

public class UniversityNotFoundException extends RuntimeException {
    public UniversityNotFoundException (String message){
        super(message);
    }
}
