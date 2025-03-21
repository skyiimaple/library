package com.chuanshuke.framework.domain.ucenter.request;

import com.chuanshuke.framework.model.request.RequestData;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest extends RequestData {
	String username;
	String password;
	String verifycode;
}
