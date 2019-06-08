package com.springpractice.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final Logger logger = LogManager.getLogger(Controller.class);

	@GetMapping("/hello")
	public ResponseEntity<String> getHello() {
		logger.info("Logger test here");
		return ResponseEntity.ok("Hello world!");
	}
}
