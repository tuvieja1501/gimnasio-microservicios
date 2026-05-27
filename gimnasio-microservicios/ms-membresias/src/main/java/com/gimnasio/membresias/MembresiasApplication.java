package com.gimnasio.membresias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MembresiasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MembresiasApplication.class, args);
    }
}
