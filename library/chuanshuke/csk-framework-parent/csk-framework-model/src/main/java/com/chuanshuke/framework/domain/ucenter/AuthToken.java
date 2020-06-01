package com.chuanshuke.framework.domain.ucenter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AuthToken {
	
	String access_token;  //身份token
	String refresh_token; //刷新token
	String jwt_token;     //jwt 令牌

}
