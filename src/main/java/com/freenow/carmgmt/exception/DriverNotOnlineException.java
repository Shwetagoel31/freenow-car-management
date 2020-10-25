package com.freenow.carmgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Driver status should be ONLINE")
public class DriverNotOnlineException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public DriverNotOnlineException(String message)
    {
        super(message);
    }

}
