package com.sparta.hhblog.dto;

import lombok.Getter;

// Dto 의 역할 : entity 를 직접 조작하지 않기 위해 데이터를 담을 객체집합을 만들어 사용함
// 목적에 맞게 RequestDto, ResponseDto 등이 있고 dto 마다 담겨있는 데이터가 다르다 (요청사항에 맞게 수정할 수 있기 때문)
@Getter
public class ArticleRequestDto {
    private String title;
    private String author;
    private String contents;
    private String password;
}
