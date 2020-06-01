package com.chuanshuke.api.ucenter;

import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.domain.ucenter.response.UcenterResult;
import com.chuanshuke.framework.model.response.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "用戶中心")
public interface UserControllerApi {
	
	@ApiOperation("根据用户账号查询用户信息")
	@ApiImplicitParam(name = "username", value = "用户名", paramType = "query",dataType = "String")
	public UcenterResult getUser(String username);
	
	@ApiOperation("用户注册")
	public ResponseResult insertUser(TbUser user);

}
