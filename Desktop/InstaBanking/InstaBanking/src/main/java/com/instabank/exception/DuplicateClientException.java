package com.instabank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicateClientException extends Exception {
	private static final long serialVersionUID = 1L;
    public DuplicateClientException(String message){
        super(message);
    }

}
