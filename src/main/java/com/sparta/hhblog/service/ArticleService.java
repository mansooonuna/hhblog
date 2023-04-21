package com.sparta.hhblog.service;

import com.sparta.hhblog.Entity.Article;
import com.sparta.hhblog.dto.ArticleRequestDto;
import com.sparta.hhblog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service                                                                                // 서비스 레이어, 내부에서 자바 로직 처리할 거라고 빈 생성해줌
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;                                  // 의존성 주입 (필드)


    // 게시물 전체 조회 - 내림차순으로
    @Transactional(readOnly = true)                                                     // 트랙잭션을 읽기 전용 모드로 설정, 읽기 전용 쿼리 성능 최적화 시킬 수 있음
    public List<Article> getArticles() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }


    // 게시글 세부 조회
    @Transactional(readOnly = true)
    public Article getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        return articleRepository.getReferenceById(id);                              // .getById(id) 도 사용은 하지만 Deprecated 어노테이션이 붙어있음 (더이상 사용하지 않게되었다는 뜻)

// article 을 articleRepository 에서 Id값으로 찾아오고
// article 이 생성되지 않았다면 예외처리
// 아래의 코드를 간결하게 람다식을 사용하여 위의 코드로 처리
//        Article article = articleRepository.findById(id);
//        if (article == null) {
//            throw new IllegalArgumentException("글이 존재하지 않습니다.");
//        }
//        return articleRepository.getReferenceById(id);

    }

    // 게시글 작성
    @Transactional                                                  // 트랜잭션 처리를 도와줌, 중간에 작업이 실패하면 이전의 상태로 되돌려줌
    public Article writeArticle(ArticleRequestDto requestDto) {
        Article article = new Article(requestDto);
        articleRepository.save(article);
        return article;
    }

    // 게시글 수정
    @Transactional
    public String update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        if (!requestDto.getPassword().equals(article.getPassword())) {              // requestDto 에 담겨 넘어온 데이터의 password와 DB에 저장되었던 객체의 password를 비교
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        } else {
            article.update(requestDto);
            return article.getId() + "번 글이 수정되었습니다.";
        }
    }

    // 게시글 삭제
    @Transactional
    public String deleteArticle(Long id, String password) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        if (!article.getPassword().equals(password)) {
            return "비밀번호가 일치하지 않습니다.";
        } else {
            articleRepository.deleteById(id);
            return id + "번 글이 삭제되었습니다.";
        }
    }
}
