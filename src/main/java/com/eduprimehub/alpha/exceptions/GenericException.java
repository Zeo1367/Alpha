package com.eduprimehub.alpha.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericException extends RuntimeException{
    private static final long serialVersionUID = -2159462266836978125L;
    private String result;
    private transient HttpStatus status; //http status code
    private transient String code;

    public GenericException(final String result, HttpStatus status, String code) {
        super(status.getReasonPhrase());
        this.result = result;
        this.status = status;
        this.code = code;
    }

}
