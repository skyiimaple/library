package com.chuanshuke.framework.domain.cms.response;

import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmsBookCatResult extends ResponseResult {
	
	TbBookCat tbBookCat;
	
	public CmsBookCatResult(ResultCode resultCode, TbBookCat tbBookCat) {
		super(resultCode);
		this.tbBookCat = tbBookCat;
	}

}
