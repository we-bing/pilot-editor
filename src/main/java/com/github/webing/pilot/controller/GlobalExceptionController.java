package com.github.webing.pilot.controller;


import com.github.webing.pilot.exception.InvalidUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by KD4 on 16. 2. 18..
 */

@ControllerAdvice
public class GlobalExceptionController {


    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionController.class);



    @ExceptionHandler(RuntimeException.class)
    public ModelAndView catchRuntimeException(RuntimeException e) {
        logger.debug(e.getMessage());
        return new ModelAndView("/errors/failure").addObject("errorlog", "파라미터 값이 올바르지 않습니다.");
    }

    @ExceptionHandler(InvalidUserException.class)
    public ModelAndView catchInvalidException(InvalidUserException e) {
        logger.debug(e.getMessage());
        return new ModelAndView("/errors/failure").addObject("errorlog", e.getMessage());
    }

}
