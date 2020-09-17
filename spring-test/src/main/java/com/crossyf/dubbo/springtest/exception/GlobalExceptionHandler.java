package com.crossyf.dubbo.springtest.exception;

import com.alibaba.fastjson.JSONObject;
import com.crossyf.dubbo.springtest.model.ReturnMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler(HttpServletRequest request, Exception e) {
        return ReturnMessage.createReturnMessage("4000", e.getMessage());
    }*/
}
