package com.example.timo_demo.web_socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run the spring application, for connecting two processes on one socket
 */

@SpringBootApplication
public class TimoDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TimoDemoApplication.class, args);
	}

}
