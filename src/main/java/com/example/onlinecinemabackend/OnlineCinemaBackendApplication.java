package com.example.onlinecinemabackend;


import com.example.onlinecinemabackend.service.FilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCinemaBackendApplication implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(OnlineCinemaBackendApplication.class, args);
		System.out.println("Server is started at http://localhost:8080");
	}
	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
