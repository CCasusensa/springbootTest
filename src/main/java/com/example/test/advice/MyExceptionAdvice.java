package com.example.test.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        System.out.println(String.format("全域異常捕獲 %s:", e.getMessage()));
        return "發生未知的錯誤，請回報給管理員!";
    }
}
