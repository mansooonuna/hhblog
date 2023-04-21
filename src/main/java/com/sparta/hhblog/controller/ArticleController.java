package com.sparta.hhblog.controller;

import com.sparta.hhblog.Entity.Article;
import com.sparta.hhblog.dto.ArticleRequestDto;
import com.sparta.hhblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController                         // RestController :  @Controller + @ResponseBody RESTful 웹 서비스를 쉽게 개발하도록 추가됨
                                        //                   객체 데이터를 JSON 또는 XML 로 담아서 전송함 -> 이 프로젝트에서는 반환이 전부 JSON이기 때문에 사용
                                        // @Controller : Model 객체를 만들어 데이터를 담고 View를 찾아줌 -> 그래서 뷰가 없는 프로젝트에서는 오류가 발생함
@RequiredArgsConstructor                // 필수값을 인자로한 생성자 자동 생성
public class ArticleController {

    private final ArticleService articleService;                                                   // articleService 의존성 주입 (필드)


    // 전체 게시글 목록 조회
    @GetMapping("/api/articles")                                                                // GET 요청이 오면 전체 내역을 조회하기 때문에 List로 반환한다.
    public List<Article> getArticles() {
        return articleService.getArticles();
    }


    // 게시글 세부 조회
    @GetMapping("/api/articles/{id}")                                                           // URI의 가변수인 {id}를 사용하여 해당 id를 가진 글을 매핑해주겠다.
    public Article getArticle(@PathVariable Long id) {                                            // @PathVariable : {변수} 에 사용될 변수 입력
        return articleService.getArticle(id);
    }

    // 게시글 작성
    @PostMapping("/api/articles")                                                               // 글을 작성해야하므로 POST 메소드 매핑
    public Article writeArticle(@RequestBody ArticleRequestDto requestDto) {                      // 작성할 글의 데이터를 Dto 에 담아 전송
        return articleService.writeArticle(requestDto);
    }


    // 게시글 수정
    @PutMapping("/api/articles/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/articles/{id}")
    public String deleteArticle(@PathVariable Long id, HttpServletRequest request) {                // HttpServletRequest 를 통해 객체의 모든 데이터가 넘어감
        String password = request.getParameter("password");                                   // 글을 삭제할 때 필요한 비밀번호만 뽑아서
        if (password == null || password.isEmpty()) {                                               // 비밀번호 유효성 검사를 간단히 하고 넘김
            return "비밀번호를 입력해주세요.";
        }
        return articleService.deleteArticle(id, password);
    }

}
