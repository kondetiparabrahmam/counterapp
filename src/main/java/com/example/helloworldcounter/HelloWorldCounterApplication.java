package com.example.helloworldcounter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ComponentScan("com.example.helloworldcounter")
@SpringBootApplication
public class HelloWorldCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldCounterApplication.class, args);
		System.out.println("Hello, World!");
	}

}
