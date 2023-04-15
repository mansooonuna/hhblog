package com.sparta.hhblog.controller;

import com.sparta.hhblog.Entity.Article;
import com.sparta.hhblog.dto.ArticleRequestDto;
import com.sparta.hhblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    // 서비스단과 연결
    private final ArticleService articleService;

    // 메인페이지
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    // 전체 게시글 목록 조회
    @GetMapping("/api/articles")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    // 게시글 세부 조회
    @GetMapping("/api/articles/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    // 게시글 작성
    @PostMapping("/api/articles")
    public Article writeArticle(@RequestBody ArticleRequestDto requestDto) {
        return articleService.writeArticle(requestDto);
    }


    // 게시글 수정
    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }


    // 게시글 삭제
    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

}
