package com.tservice.sbg.trace;

import datadog.trace.api.interceptor.MutableSpan;
import datadog.trace.api.interceptor.TraceInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StatusInterceptor implements TraceInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusInterceptor.class);
    @Override
    public Collection<? extends MutableSpan> onTraceComplete(Collection<? extends MutableSpan> collection) {
        List<String> statusCodes = List.of("NOT_FOUND");
        return collection.stream().map((span)->{
                if (span.isError() && statusCodes.contains((String)span.getTags().get("status.code"))){
                    LOGGER.info("status code NOT_FOUND not an error..");
                    return span.setError(false);
                }
                else {
                    return span;
                }
        }).collect(Collectors.toList());
    }

    @Override
    public int priority() {
        return 10;
    }
}
