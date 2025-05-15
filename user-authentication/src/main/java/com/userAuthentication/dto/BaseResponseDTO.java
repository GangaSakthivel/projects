package com.userAuthentication.dto;

public class BaseResponseDTO<T> extends ResponseDTO{
    public BaseResponseDTO() {
        super();
    }

    public BaseResponseDTO(Boolean status, String message, T data) {
        super(status, message, data);
    }
}
