package com.travel.travtronics.config;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class HeaderTokenInterceptor implements ClientHttpRequestInterceptor {

	public static final String AUTHORIZATION = "Authorization";

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpServletRequest previousRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		if (previousRequest.getHeader(AUTHORIZATION) != null) {
			request.getHeaders().add(AUTHORIZATION, previousRequest.getHeader(AUTHORIZATION));
		}
		return execution.execute(request, body);
	}

}
