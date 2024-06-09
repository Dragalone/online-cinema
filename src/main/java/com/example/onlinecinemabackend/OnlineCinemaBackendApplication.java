package com.example.onlinecinemabackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCinemaBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineCinemaBackendApplication.class, args);
		System.out.println("Server is started at http://localhost:8080");
	}

}
