package com.tservice.httpserver;

import com.tservice.httpserver.interceptor.StatusTraceInterceptor;
import datadog.trace.api.GlobalTracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String []args){
        SpringApplication.run(GrpcClientApplication.class, args);
        GlobalTracer.get().addTraceInterceptor(new StatusTraceInterceptor());
    }
}
