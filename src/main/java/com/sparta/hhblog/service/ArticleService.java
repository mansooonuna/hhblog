package com.sparta.hhblog.service;

import com.sparta.hhblog.Entity.Article;
import com.sparta.hhblog.dto.ArticleRequestDto;
import com.sparta.hhblog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    // repository 연결
    private final ArticleRepository articleRepository;

    // 게시물 전체 조회 - 내림차순으로
    @Transactional(readOnly = true)
    public List<Article> getArticles() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 세부 조회
    @Transactional(readOnly = true)
    public Article getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        return articleRepository.getReferenceById(id);
    }

    // 게시글 작성
    @Transactional
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
        if (!requestDto.getPassword().equals(article.getPassword())) {
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
