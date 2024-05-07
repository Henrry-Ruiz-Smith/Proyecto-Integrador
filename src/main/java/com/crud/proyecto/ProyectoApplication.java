package com.crud.proyecto;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
		System.out.println("SISTEMA ESTADO : OKI");
	}

	
    @PostConstruct
    void started() {
        // Establecer la zona horaria a "America/Lima" (Perú)
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
    }

}
