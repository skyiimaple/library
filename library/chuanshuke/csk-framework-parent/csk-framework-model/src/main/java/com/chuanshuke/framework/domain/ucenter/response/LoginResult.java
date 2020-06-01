package com.chuanshuke.framework.domain.ucenter.response;

import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginResult extends ResponseResult {
	private String token;

	public LoginResult(ResultCode resultCode, String token) {
		super(resultCode);
		this.token = token;
	}
	
}
