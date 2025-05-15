package com.userAuthentication.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {

    private Boolean status;
    private String message;
    private T data;

    public ResponseDTO() {
        super();
    }

    public ResponseDTO(Boolean status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
