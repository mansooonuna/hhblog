package com.sparta.hhblog.repository;

import com.sparta.hhblog.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 특별한 어노테이션을 붙이지 않아도 JpaRepository를 상속받으면 JPA의 구현체가 애플리케이션 실행 시점에
// 자주 사용되는 쿼리 집합을 만들어준다.
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByCreatedAtDesc();
    // 메소드로 조건을 여러개 설정하면 이름이 길어지는 단점이 발생했다.
    // 그래서 커스텀 쿼리를 사용할 수 있게 되었다?
    // 여기서는 find + All + By + OrderBy + CreatedAt + Dest
    // 모든 객체를 생성날짜 기준으로 내림차순 정렬하여 list에 담아 보낸다라는 뜻
}
