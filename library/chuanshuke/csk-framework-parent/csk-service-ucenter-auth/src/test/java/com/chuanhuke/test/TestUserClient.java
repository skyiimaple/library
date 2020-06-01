package com.chuanhuke.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chuanshuke.auth.UcenterAuthApplication;
import com.chuanshuke.auth.client.UserClient;
import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.domain.ucenter.response.UcenterResult;

@SpringBootTest(classes = UcenterAuthApplication.class)
@RunWith(SpringRunner.class)
public class TestUserClient {
	
	@Autowired
	UserClient userClient;
	
	@Test
	public void testGetUser() {
		String username = "lyt";
		UcenterResult result = userClient.getUser(username);
		TbUser tbUser = result.getTbUser();
		System.out.println(tbUser);
	}

}
