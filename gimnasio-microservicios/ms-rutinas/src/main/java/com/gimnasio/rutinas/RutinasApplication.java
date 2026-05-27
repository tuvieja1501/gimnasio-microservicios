package com.gimnasio.rutinas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RutinasApplication {

    public static void main(String[] args) {
        SpringApplication.run(RutinasApplication.class, args);
    }
}
