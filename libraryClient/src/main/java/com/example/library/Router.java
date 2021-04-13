package com.example.library;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.library.controllers.BookController;

@SpringBootApplication
public class Router 
{
	public static void main(String[] args) throws IOException {
		
		SpringApplication.run(Router.class, args);
		}
	
	
}
