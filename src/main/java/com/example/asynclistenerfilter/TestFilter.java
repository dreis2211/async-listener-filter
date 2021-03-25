package com.example.asynclistenerfilter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(asyncSupported = false)
public class TestFilter extends GenericFilterBean {

	public TestFilter() {
		System.out.println("init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!request.isAsyncStarted()) {
			System.out.println("Do normal work");
		} else {
			request.getAsyncContext().addListener(new AsyncListener() {
				@Override
				public void onComplete(AsyncEvent event) throws IOException {
					System.out.println("onComplete called");
				}

				@Override
				public void onTimeout(AsyncEvent event) throws IOException {
					System.out.println("onTimeout called");
				}

				@Override
				public void onError(AsyncEvent event) throws IOException {
					System.out.println("onError called");
				}

				@Override
				public void onStartAsync(AsyncEvent event) throws IOException {

				}
			});
		}
	}

}
