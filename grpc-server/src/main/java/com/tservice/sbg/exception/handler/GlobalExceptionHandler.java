package com.tservice.sbg.exception.handler;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @GrpcExceptionHandler(StatusRuntimeException.class)
    public Status handleStatusRuntimeException(StatusRuntimeException ex){
        LOGGER.info("Status runtime exception handle..");
        return ex.getStatus().withDescription(ex.getMessage()).withCause(ex);
    }
}
