package com.hdm.restdemo;

import com.hdm.restdemo.config.HttpsClientRequestFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		// 默认的RestTemplate，底层是走JDK的URLConnection方式。
		//return new RestTemplate();

		return new RestTemplate(new HttpsClientRequestFactory());
	}

}
