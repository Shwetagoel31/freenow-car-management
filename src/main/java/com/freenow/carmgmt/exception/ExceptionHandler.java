package com.freenow.carmgmt.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        LOG.info("REEH exception caught");
        LOG.info(ex.toString());
        LOG.info(ex.getMessage());
        LOG.info(headers.toString());
        LOG.info(status.name());
        LOG.info(request.toString());
        LOG.info(request.getContextPath());
        LOG.info(request.getDescription(true));
        LOG.info(request.getRemoteUser());
        LOG.info(request.getSessionId());
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

}
