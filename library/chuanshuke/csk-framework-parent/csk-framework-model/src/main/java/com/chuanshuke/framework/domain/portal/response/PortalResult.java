package com.chuanshuke.framework.domain.portal.response;

import com.chuanshuke.framework.domain.portal.BookInfo;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.model.response.ResultCode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PortalResult extends ResponseResult {
	private BookInfo bookInfo;

	public PortalResult(ResultCode resultCode, BookInfo bookInfo) {
		super(resultCode);
		this.bookInfo = bookInfo;
	}
	
}
