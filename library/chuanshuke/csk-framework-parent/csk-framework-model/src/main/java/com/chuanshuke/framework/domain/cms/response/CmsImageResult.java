package com.chuanshuke.framework.domain.cms.response;

import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CmsImageResult extends ResponseResult {
	
	private String url;

	public CmsImageResult(ResultCode resultCode, String url) {
		super(resultCode);
		this.url = url;
	}
	
}
