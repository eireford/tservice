package com.tservice.httpserver.mapper;

import com.tservice.proto.Author;
import com.tservice.httpserver.model.AuthorModel;
import org.springframework.stereotype.Component;

@Component
public class BookAuthorMapper {

    public AuthorModel toAuthorModel(Author authorProto){
       return AuthorModel.builder()
                .authorId(authorProto.getAuthorId())
                .firstName(authorProto.getFirstName())
                .lastName(authorProto.getLastName())
                .gender(authorProto.getGender())
                .bookId(authorProto.getBookId())
                .build();
    }
}
