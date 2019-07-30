package com.exadelinternship.carpool.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
//@EnableWebMvc
public class ExceptionController {
    private  Logger logger = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler (value=Exception.class)
    public String handleError(HttpServletRequest req,Exception exception){
        logger.error("Request: "+req.getRequestURL()+" raised "+exception);
        return "error";
    }
}
