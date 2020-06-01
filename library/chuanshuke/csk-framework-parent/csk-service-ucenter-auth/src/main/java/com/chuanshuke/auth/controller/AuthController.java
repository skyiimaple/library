package com.chuanshuke.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chuanshuke.api.auth.AuthControllerApi;
import com.chuanshuke.auth.service.AuthService;
import com.chuanshuke.framework.domain.ucenter.AuthToken;
import com.chuanshuke.framework.domain.ucenter.JwtResult;
import com.chuanshuke.framework.domain.ucenter.request.LoginRequest;
import com.chuanshuke.framework.domain.ucenter.response.AuthCode;
import com.chuanshuke.framework.domain.ucenter.response.LoginResult;
import com.chuanshuke.framework.exception.ExceptionCast;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.utils.CookieUtil;

@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {
	
	@Value("${auth.clientId}")
	String clientId;
	
	@Value("${auth.clientSecret}")
	String clientSecret;
	
	@Value("${auth.cookieDomain}")
	String cookieDomain;
	
	@Value("${auth.cookieMaxAge}")
	int cookieMaxAge;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/userlogin")
	@Override
	public LoginResult login(LoginRequest loginRequest) {
		if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())) {
			ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
		}
		if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())) {
			ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
		}
		//账号
		String username = loginRequest.getUsername();
		//密码
		String password = loginRequest.getPassword();
		//申请令牌
		AuthToken authToken = authService.login(username, password, clientId, clientSecret);
		//用户身份令牌
		String access_token = authToken.getAccess_token();
		//将令牌存储到cookie
		this.saveCookie(access_token);
		return new LoginResult(CommonCode.SUCCESS, access_token);
	}
	
	//将令牌存储到cookie
	private void saveCookie(String token) {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		//httpservletresponse response, string domain, string path, string name, string value, int maxage, boolean httponly
		//最后一个参数表示是否允许浏览器获取
		CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);
	}

	@PostMapping("/userlogout")
	@Override
	public ResponseResult logout() {
		//取出cookie中的用户身份令牌
		String uid = getTokenFromCookie();
		//删除redis中的token
		boolean result = authService.delToken(uid);
		//清除cookie
		this.clearCookie(uid);
		return new ResponseResult(CommonCode.SUCCESS);
	}

	//从cookie删除token
	private void clearCookie(String uid) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		//httpservletresponse response, String domain, String path, String name, String value, int maxAge, boolean httpOnlh;
		CookieUtil.addCookie(response, cookieDomain, "/", "uid", uid, 0, false);
	}

	@GetMapping("/userjwt")
	@Override
	public JwtResult userJwt() {
		//取出cookie中的用户身份令牌
		String uid = getTokenFromCookie();
		if(uid == null) {
			return new JwtResult(CommonCode.FAIL, null);
		}
		
		//拿身份令牌从redis中查询jwt令牌
		AuthToken userToken = authService.getUserToken(uid);
		if(userToken != null) {
			//将jwt令牌返回给用户
			String jwt_token = userToken.getJwt_token();
			return new JwtResult(CommonCode.SUCCESS, jwt_token);
		}
		return null;
	}
	
	//取出cookie中的身份令牌
	private String getTokenFromCookie() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Map<String, String> map = CookieUtil.readCookie(request, "uid");
		if(map != null && map.get("uid") != null) {
			String uid = map.get("uid");
			return uid;
		}
		return null;
	}

}
