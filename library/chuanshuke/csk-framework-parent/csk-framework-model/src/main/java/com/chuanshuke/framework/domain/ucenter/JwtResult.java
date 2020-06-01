package com.chuanshuke.framework.domain.ucenter;

import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class JwtResult extends ResponseResult {
	
	private String jwt;

	public JwtResult(ResultCode resultCode, String jwt) {
		super(resultCode);
		this.jwt = jwt;
	}
	
}
