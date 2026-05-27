package com.gimnasio.asistencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AsistenciasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsistenciasApplication.class, args);
    }
}
