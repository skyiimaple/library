package com.chuanhuke.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.chuanshuke.auth.UcenterAuthApplication;

@SpringBootTest(classes = UcenterAuthApplication.class)
@RunWith(SpringRunner.class)
public class TestRedis {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void testRedis() {
		//定义key
		String key = "user_token:5a59120a-9446-4cff-b9f2-b7b33ffeb917";
		//定义map
		Map<String, String> mapValue = new HashMap<String, String>();
		mapValue.put("id", "101");
		mapValue.put("username", "zhangsan");
		String value = JSON.toJSONString(mapValue);
		//向redis中存储字符串
		stringRedisTemplate.boundValueOps(key).set(value, 60, TimeUnit.SECONDS);
		//读取过期时间，已过期返回-2
		Long expire = stringRedisTemplate.getExpire(key);
		//根据key获取value
		String s = stringRedisTemplate.opsForValue().get(key);
		System.out.println(s);
		
	}
	

}
