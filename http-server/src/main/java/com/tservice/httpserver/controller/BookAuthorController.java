package com.tservice.httpserver.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tservice.httpserver.model.AuthorModel;
import com.tservice.httpserver.services.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookAuthorController {

    @Autowired
    BookAuthorService bookAuthorService;

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorModel> getAuthor(@PathVariable String id) {
        return bookAuthorService.getAuthor(Integer.parseInt(id));
    }

}
