package com.tservice.sbg.service;

import com.tservice.api.Author;
import com.tservice.api.AuthorRequest;
import com.tservice.api.BookAuthorServiceGrpc;
import com.tservice.sbg.mapper.BookAuthorMapper;
import com.tservice.sbg.model.AuthorModel;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorService {
    private static Logger LOGGER = LoggerFactory.getLogger(BookAuthorService.class);

    @GrpcClient("grpc-server-service")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub blockingStub;

    @Autowired
    BookAuthorMapper bookAuthorMapper;

    public ResponseEntity<AuthorModel> getAuthor(int authorId){
        try{
            Author author = blockingStub.getAuthor(AuthorRequest.newBuilder().setAuthorId(authorId).build());
            return new ResponseEntity<>(bookAuthorMapper.toAuthorModel(author), HttpStatus.OK) ;
        }
        catch (StatusRuntimeException ex){
            LOGGER.error(ex.getStatus().getDescription());
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
