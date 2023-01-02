package com.example.linecloneback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LineCloneBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LineCloneBackApplication.class, args);
    }

}
