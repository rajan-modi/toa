package com.tao.taskproject.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHand{

    @ExceptionHandler
    public Object handleRequestValidationException(Exception ex, HttpServletRequest request){
    Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("Status" , HttpStatus.BAD_REQUEST);
        return responseBody;
    }

}
