package com.sparta.hhblog.dto;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private String title;
    private String writerName;
    private String contents;
}
