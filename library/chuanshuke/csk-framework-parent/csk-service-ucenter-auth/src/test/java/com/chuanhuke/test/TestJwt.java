package com.chuanhuke.test;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateCrtKey;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

@SpringBootTest(classes = TestJwt.class)
@RunWith(SpringRunner.class)
public class TestJwt {
	
	//创建jwt令牌
	@Test
	public void testCreateJwt() {
		//密钥库文件
		String keystore = "csk.keystore";
		//密钥库的密码
		String keystore_password = "chuanshukekeystore";
		//密钥库文件路径
		ClassPathResource classPathResource = new ClassPathResource(keystore);
		//密钥别名
		String alias = "cskkey";
		//密钥的访问密码
		String key_password = "chuanshuke";
		//密钥工厂
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keystore_password.toCharArray());
		//密钥对（公钥和私钥）
		KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
		//获取私钥
		RSAPrivateCrtKey aPrivate = (RSAPrivateCrtKey) keyPair.getPrivate();
		//jwt令牌的内容
		Map<String, String> body = new HashMap<String, String>();
		body.put("name", "itcast");
		String bodyString = JSON.toJSONString(body);
		//生成jwt令牌
		Jwt jwt = JwtHelper.encode(bodyString, new RsaSigner(aPrivate));
		//生成jwt令牌编码
		String encoded = jwt.getEncoded();
		System.out.println(encoded);
		
	}
	
	//校验jwt令牌
	@Test
	public void testVerify() {
		//公钥
		String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvKMHgFp1g0D523M/z39uM4rhLj8WMGALPTESLdesNib4YArZ0DCRoauc5wR9K1xqntJtjSXv6UB8uF5wKACgo3EwSPS1G0C0hu0IY5NOT5v4mnovb6SL+fH2OPLN69ZZfOpeTzdX+nCiCOKltB0yZg+s+wSfBR9VX1bLOlJcHpMPqqn//DCQNKDxTgw1aQTAgjga5VeKF727tSmsR3r5pZJJVy5s9iW8JO/ypSxxrvryp+bSQnxczlxaArwmaGJkU3Z7yoMkR3IV1v+T/HXv9oYExElgoHQC2wRex4+8QqUKvDZDJCZp2qXr1TeSN/5HlpexxvSUtoJsH8MQiilXmwIDAQAB-----END PUBLIC KEY-----";
		//jwt令牌
		String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiaXRjYXN0In0.U7ujs38zHOuATN2eTnRHqRTeaDb0l1PyReNxv0ju3EDgKhzMB71JNQ4IpV2iPU_5NjAOP6_86W5I-X9AfFtaE_14nFby5k9-cCxQSTSUwM_mDHS7N3Wbgb_d3uSncEhl9Bkt3ni6wE3CCNuZABsd07DkDyAvL4xhBnefcIPc2FVfp7qMUE5Ka1XsXLxtdj-hKxKfENoYXU21z5NmOVmt-vgUWBN0RhlsLHDI9AG4YGQtLu8C7g4GCr5ulHwNpcx5qw0Ah3ZFQL2EDoADEHc0EXfxqv00DxInehVXdsVFFAppjHUsrqfeV63iKe6VT_Y0ca8muddSsCO4gM-VoKULFg";
		//校验jwt令牌
		Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
		//拿到jwt令牌中自定义的内容
		String claims = jwt.getClaims();
		System.out.println(claims);
	}
	
}
