package com.springpractice.api.controller;

import com.springpractice.api.service.QueueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final Logger logger = LogManager.getLogger(Controller.class);

	@Autowired
	private QueueService queueService;

	@GetMapping("/hello")
	public ResponseEntity<String> getHello() {
		logger.info("Logger test here");
		return ResponseEntity.ok("Hello world!");
	}

	@PostMapping("/message/{message}")
	public ResponseEntity<String> rabbitmqTest(@PathVariable String message) {
		logger.info("Pushing message -> {} to test queue.", message);
		queueService.pushTest(message);
		return ResponseEntity.ok("Pushed message to test queue");
	}
}
