package com.tservice.sbg.exception;

import com.google.protobuf.Any;
import com.google.rpc.ErrorInfo;
import com.google.rpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;

public class StatusRuntimeExceptionBuilder {
    public static StatusRuntimeException build(int status, String domain, String message){
        Status status1 = Status.newBuilder()
                .setCode(status)
                .setMessage(message)
                .addDetails(Any.pack(ErrorInfo.newBuilder().setDomain(domain).setReason(message).build()))
                .build();
        return toStatusRuntimeException(status1);
    }

    private static StatusRuntimeException toStatusRuntimeException(Status status){
        return StatusProto.toStatusRuntimeException(status);
    }
}
