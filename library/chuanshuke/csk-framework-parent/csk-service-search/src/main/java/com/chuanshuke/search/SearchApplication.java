package com.chuanshuke.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.chuanshuke.framework.domain")
@ComponentScan("com.chuanshuke.search")
@ComponentScan("com.chuanshuke.api")
@ComponentScan("com.chuanshuke.framework")
public class SearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate() {
//		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//	}

}
