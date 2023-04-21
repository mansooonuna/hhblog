package com.sparta.hhblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing                      //이 어노테이션을 사용해야 생성일자, 수정일자 자동으로 등록 가능
@SpringBootApplication                  // 스프링 부트의 가장 기본적인 설정을 선언해준다.
public class HhblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhblogApplication.class, args);
    }

}
