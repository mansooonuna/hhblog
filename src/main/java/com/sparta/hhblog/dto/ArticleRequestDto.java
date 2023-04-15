package com.sparta.hhblog.dto;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private String title;
    private String author;
    private String contents;
    private String password;
}
