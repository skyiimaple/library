package com.chuanshuke.framework.domain.cms.response;

import com.chuanshuke.framework.domain.cms.TbComment;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmsCommentResult extends ResponseResult {
	
	TbComment tbComment;
	
	public CmsCommentResult(ResultCode resultCode, TbComment tbComment) {
		super(resultCode);
		this.tbComment = tbComment;
	}

}
