package com.example.asynclistenerfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@SpringBootTest
class AsyncListenerFilterApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mvc;

	@BeforeEach
	void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				//.addFilters(new TestFilter(), springSecurityFilterChain)
				//.addFilters(new TestFilter())
				.build();
	}

	@Test
	void testFilter() throws Exception {
		MvcResult result = mvc.perform(get("/hello"))
				.andExpect(request().asyncStarted())
				.andReturn();

		mvc.perform(asyncDispatch(result)).andExpect(r -> {
			Object asyncResult = r.getAsyncResult();
			assertThat(asyncResult).isEqualTo("Hello");
		});
	}

}
