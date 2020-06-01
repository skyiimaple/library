package com.chuanshuke.framework.domain.ucenter.response;


import com.chuanshuke.framework.model.response.ResultCode;

public enum UcenterCode implements ResultCode {
	UCENTER_USERNAME_NONE(false,23001,"请输入账号！"),
    UCENTER_PASSWORD_NONE(false,23002,"请输入密码！"),
    UCENTER_VERIFYCODE_NONE(false,23003,"请输入验证码！"),
    UCENTER_ACCOUNT_NOTEXISTS(false,23004,"账号不存在！"),
    UCENTER_CREDENTIAL_ERROR(false,23005,"账号或密码错误！"),
    UCENTER_LOGIN_ERROR(false,23006,"登陆过程出现异常请尝试重新操作！"),
    UCENTER_USERNAME_ISEXIST(false, 23007, "用户名已存在");
	
	boolean success;
	
	int code;
	
	String message;
	
	
	private UcenterCode(boolean success, int code, String message) {
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
