package com.gimnasio.instructores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InstructoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstructoresApplication.class, args);
    }
}
