package com.tservice.httpserver.model;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {
    private int authorId;
    private String firstName;
    private String lastName;
    private String gender;
    private int bookId;
}
