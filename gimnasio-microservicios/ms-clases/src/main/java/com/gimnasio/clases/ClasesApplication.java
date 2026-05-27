package com.gimnasio.clases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClasesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClasesApplication.class, args);
    }
}
