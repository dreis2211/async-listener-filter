package com.example.asynclistenerfilter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class TestController {

	@RequestMapping("/hello")
	public CompletableFuture<String> hello() {
		CompletableFuture<String> future = new CompletableFuture<>();
		future.complete("Hello");
		return future;
	}

}
