package com.example.mutantdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutantDetectorAPI {

	public static void main(String[] args) {
		SpringApplication.run(MutantDetectorAPI.class, args);
		System.out.println("La API esta lista para detectar ADN-mutantes");
	}

}
