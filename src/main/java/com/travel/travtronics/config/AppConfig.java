package com.travel.travtronics.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate() {

		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory);
		simpleClientHttpRequestFactory.setOutputStreaming(false);
		simpleClientHttpRequestFactory.setConnectTimeout(1000);
		simpleClientHttpRequestFactory.setReadTimeout(1000);
		                                                                                                                                                                       

		RestTemplate restTemplate = new RestTemplate(factory);

		//restTemplate.setRequestFactory(factory);

		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

		interceptors.add(new HeaderTokenInterceptor());
		interceptors.add(new RequestResponseLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);   

		return restTemplate; 
	}

}
