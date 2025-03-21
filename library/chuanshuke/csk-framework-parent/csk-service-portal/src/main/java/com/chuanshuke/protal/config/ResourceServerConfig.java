package com.chuanshuke.protal.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)  //方法认证
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	//公钥
	private static final String PUBLIC_KEY = "publickey.txt";
	
	//定义jwtTokenStore，使用jwt令牌
	@Bean
	public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}
	
	//定义JwtAccessTokenConverter,使用jwt令牌
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setVerifierKey(getPubKey());
		return converter;
	}
	
	/**
	 * 获取非对称加密公钥 Key
	 * @return 公钥 key
	 */
	private String getPubKey() {
		Resource resource = new ClassPathResource(PUBLIC_KEY);
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(resource.getInputStream());
			BufferedReader br = new BufferedReader(inputStreamReader);
			return br.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//http安全配置，对每个到达系统的http请求连接进行校验
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//所有请求必须认证通过
		http.authorizeRequests()
			//下边的路径放行
//			.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
//					"/swagger-resources", "/swagger-resources/configuration/security",
//					"/swagger-ui.html", "/webjars/**").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated();
	}

}
