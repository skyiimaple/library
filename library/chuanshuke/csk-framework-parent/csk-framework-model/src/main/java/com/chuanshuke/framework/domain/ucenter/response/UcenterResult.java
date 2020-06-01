package com.chuanshuke.framework.domain.ucenter.response;

import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UcenterResult extends ResponseResult {
	
	TbUser tbUser;

	public UcenterResult(ResultCode resultCode, TbUser tbUser) {
		super(resultCode);
		this.tbUser = tbUser;
	}
	
	

}
