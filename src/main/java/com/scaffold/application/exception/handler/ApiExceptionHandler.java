package com.scaffold.application.exception.handler;

import com.scaffold.application.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(ApiException ex) {
        HashMap<String, Object> exception = new HashMap<>();
        exception.put("errorCode", ex.getHttpStatus().value());
        exception.put("message", ex.getErrMsg());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }
}