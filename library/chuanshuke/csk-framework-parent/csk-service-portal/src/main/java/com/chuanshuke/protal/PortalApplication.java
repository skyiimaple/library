package com.chuanshuke.protal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EntityScan("com.chuanshuke.framework.domain")
@ComponentScan("com.chuanshuke.framework")
@ComponentScan("com.chuanshuke.api")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//	}

}
