package com.tservice.sbg.grpc;

import com.tservice.proto.Author;
import com.tservice.proto.AuthorRequest;
import com.tservice.proto.BookAuthorServiceGrpc;
import com.tservice.sbg.db.TempDb;
import com.tservice.sbg.exception.StatusRuntimeExceptionBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class BookAuthorApi extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {

    @Override
    public void getAuthor(AuthorRequest request, StreamObserver<Author> responseObserver) {
         Optional<Author> author = TempDb.getAuthorsFromTempDb()
                .stream()
                .filter(data -> data.getAuthorId()==request.getAuthorId())
                .findFirst();
         if(author.isPresent()){
             responseObserver.onNext(author.get());
             responseObserver.onCompleted();
         }
         else {
             /** responseObserver.onError(StatusRuntimeExceptionBuilder.build(
                     Status.Code.NOT_FOUND.value(),
                     "book-author-service",
                     "author_id ="+request.getAuthorId()+" not found."));**/
            throw StatusRuntimeExceptionBuilder.build(
                     Status.Code.NOT_FOUND.value(),
                     "book-author-service",
                     "author_id = "+request.getAuthorId()+" not found.");
         }
    }
}
