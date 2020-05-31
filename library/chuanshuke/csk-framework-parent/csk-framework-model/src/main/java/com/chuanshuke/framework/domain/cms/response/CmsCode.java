package com.chuanshuke.framework.domain.cms.response;

import com.chuanshuke.framework.model.response.ResultCode;

import lombok.ToString;

@ToString
public enum CmsCode implements ResultCode {
	
	CMS_CAT_ISEXIT(false, 24001, "标签已存在"),
	CMS_CAT_IS_NOT_LEAF(false, 24002, "不是叶子标签"),
	CMS_BOOK_INFO_MISSING(false, 24003,"请完善图书信息"),
	CMS_BOOK_INFO_ERRO(false,24004, "图书信息有误或已存在"),
	CMS_BOOK_IS_NOT_EXIT(false, 24005, "图书不存在"),
	CMS_COMMENT_IS_NOT_EXIT_OR_DELETED(false, 24006, "评论不存在或已删除");
	
	
	boolean success;
	
	int code;
	
	String message;
	
	private CmsCode(boolean success, int code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}
	

	@Override
	public boolean success() {
		return success;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String message() {
		return message;
	}

}
