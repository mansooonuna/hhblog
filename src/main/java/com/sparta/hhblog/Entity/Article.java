package com.sparta.hhblog.Entity;

import com.sparta.hhblog.dto.ArticleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Article extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;           // 제목

    @Column(nullable = false)
    private String writerName;      // 작성자명

    @Column(nullable = false)
    private String contents;        // 작성 내용

    // 기본 생성자
    public Article(String title, String writerName, String contents) {
        this.title = title;
        this.writerName = writerName;
        this.contents = contents;
    }

    // dto 생성자
    public Article(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writerName = requestDto.getWriterName();
        this.contents = requestDto.getContents();
    }

    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writerName = requestDto.getWriterName();
        this.contents = requestDto.getContents();
    }
}
