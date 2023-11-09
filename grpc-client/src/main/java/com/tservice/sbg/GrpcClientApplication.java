package com.tservice.sbg;

import com.tservice.sbg.interceptor.StatusTraceInterceptor;
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
