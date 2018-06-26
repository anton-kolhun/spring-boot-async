package com.kolhun.exception.handler;


import com.kolhun.exception.dto.GenericError;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GenericError unhandledError(Exception ex, HttpServletResponse response) {
        String stackTrace = ExceptionUtils.getFullStackTrace(ex);
        logger.error("error occurred: " + stackTrace);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new GenericError(ex.getMessage(), ExceptionUtils.getFullStackTrace(ex));
    }

    @ExceptionHandler(value = TaskRejectedException.class)
    public ResponseEntity handlePoolLimit(TaskRejectedException ex) {
        String stackTrace = ExceptionUtils.getFullStackTrace(ex);
        logger.error("error occurred: " + stackTrace);
        return new ResponseEntity<String>(HttpStatus.TOO_MANY_REQUESTS);
    }

}
