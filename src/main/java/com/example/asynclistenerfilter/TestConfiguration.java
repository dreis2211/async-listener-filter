package com.example.asynclistenerfilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

	@Bean
	public TestFilter testFilter() {
		return new TestFilter();
	}

}
