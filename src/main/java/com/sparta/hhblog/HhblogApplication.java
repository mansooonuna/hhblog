package com.sparta.hhblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HhblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhblogApplication.class, args);
    }

}
