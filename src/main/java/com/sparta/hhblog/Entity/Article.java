package com.sparta.hhblog.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hhblog.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity                          // JPA를 사용해 테이블과 매핑할 클래스에 붙여주는 어노테이션
                                 // (name = "테이블명") 옵션으로 테이블명 지정 가능, default 값은 해당 클래스 이름, 여기서는 Article

// @Table : 엔티티와 매핑할 테이블을 지정할 수 있음, (name = "지정 테이블명") 해당 테이블과 매핑할 수 있음
@Getter                          // Lombok : getter 자동 생성
@NoArgsConstructor               // Lombok : 인자가 없는 생성자 자동 생성
public class Article extends Timestamped {
    @Id                                                 // 특정 속성을 기본키(PK)로 사용하겠다. 여기선 id
    @GeneratedValue(strategy = GenerationType.AUTO)     // 기본키 값에 대한 생성 전략 제공
                                                        // AUTO : JPA 가 자동으로 생성 전략 결정
                                                        // TABLE : DB에 키 생성 전용 테이블을 만들고 기본키를 생성한다.
                                                        // SEQUENCE : DB의 특별한 오브젝트 시퀀스를 사용하여 기본키 생성
                                                        // IDENTITY : 기본키 생성을 DB에 위임 (ex, AUTO_INCREMENT)
    private Long id;

    @Column(nullable = false)                           // DDL 생성시 not null 제약조건 추가 , 유효성 검사는 안함
                                                        //@NotNull 을 이용하면 제약조건 추가 + 유효성 검사도 같이
    private String title;

    @Column(nullable = false)
    private String writerName;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @JsonIgnore                                         // 필드 레벨에서 무시 될 수 있는 속성을 표시 -> 이 프로젝트에서는 GET 요청시 JSON 으로 보여주지 않음.
    private String password;



    // dto 생성
    public Article(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writerName = requestDto.getAuthor();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    // 글 수정
    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writerName = requestDto.getAuthor();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

}
