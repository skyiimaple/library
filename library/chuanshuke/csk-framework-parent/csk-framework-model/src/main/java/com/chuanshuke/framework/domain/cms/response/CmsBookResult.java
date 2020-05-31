package com.chuanshuke.framework.domain.cms.response;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmsBookResult extends ResponseResult {
	
	TbBook tbBook;
	
	public CmsBookResult(ResultCode resultCode, TbBook tbBook) {
		super(resultCode);
		this.tbBook = tbBook;
	}

}
