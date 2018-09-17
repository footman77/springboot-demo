package com.example.demo.exception;

import com.example.demo.pojo.MyJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice
public class MyAjaxExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
    public MyJSONResult defaultErrorHandler(HttpServletRequest req,
                                            Exception e) throws Exception {

        e.printStackTrace();
        return MyJSONResult.errorException(e.getMessage());
    }
}
