package com.jenkins.testjenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class TestJenkinsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestJenkinsApplication.class, args);
    }
}
