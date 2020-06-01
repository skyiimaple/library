package com.chuanshuke.framework.domain.portal.response;

import java.util.List;

import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PortalCommentResult extends ResponseResult {
	
	List<CommentInfo> list;

	public PortalCommentResult(ResultCode resultCode, List<CommentInfo> list) {
		super(resultCode);
		this.list = list;
	}
	
	

}
