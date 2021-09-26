package com.example.PaginationDemo.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.RESET_CONTENT)
public class ResetContent extends RuntimeException {
    public ResetContent(String message){
        super(message);
    }
}
