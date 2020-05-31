package com.chuanhuke.test;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.chuanshuke.auth.UcenterAuthApplication;
import com.chuanshuke.framework.client.CskServiceList;

@SpringBootTest(classes = UcenterAuthApplication.class)
@RunWith(SpringRunner.class)
public class TestClient {
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Test
	public void testClient() throws Exception {
		//采用客户端负载均衡，从eureka获取认证服务的ip和端口
		ServiceInstance serviceInstance = loadBalancerClient.choose(CskServiceList.CSK_SERVICE_UCENTER_AUTH);
		URI uri = serviceInstance.getUri();
		String authUrl = uri + "/auth/oauth/token";
		//URI url，HttpMethod method, HTTP Entity<?> requestEntity, Class<T> responseType
		//url 就是申请令牌的url /oauth/token
		//method http的方法类型
		//requestEntity请求内容
		//responseType，将响应的结果生成的类型
		
		//请求的内容分两部分
		//1、header信息，包括了http basic认证信息
		MultiValueMap<String, String> headers =  new LinkedMultiValueMap<String, String>();
		String httpBasic = getHttpBasic("CskWebApp", "CskWebApp");
		headers.add("Authorization", httpBasic);
		
		//2、body 包括：grant_type、username、password
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "password");
		body.add("username", "lyt");
		body.add("password", "lyt");
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String,String>>(body, headers);
		//指定restTemplate当遇到400或401响应时候也不要抛出异常，也要正常返回值
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				if(response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
					super.handleError(response);					
				}
			}
		});
		//远程调用申请令牌
		ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
		Map bodyMap = exchange.getBody();
		System.out.println(bodyMap);
	}
	
	//获取httpbasic的串
	private String getHttpBasic(String clientId, String clientSecret) {
		String string = clientId + ":" + clientSecret;
		//将串进行base64编码
		byte[] encode = Base64Utils.encode(string.getBytes());
		return "Basic " + new String(encode);
	}
	
	@Test
	public void testGetClientUri() throws IOException {
		ServiceInstance choose = loadBalancerClient.choose(CskServiceList.CSK_SERVICE_UCENTER_AUTH);
		URI uri = choose.getUri();
		System.out.println(uri.toString());
	}

}
