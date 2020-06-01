package com.chuanshuke.govern.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.govern.gateway.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class LoginFilter extends ZuulFilter {
	
	@Autowired
	AuthService authService;

	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		StringBuffer requestURL = request.getRequestURL();
		if(requestURL.indexOf("get") > 0 || requestURL.indexOf("list") > 0 || requestURL.indexOf("auth") > 0) {
//			if(requestURL.indexOf("cms") > 0) {
//				return true;
//			}
			return false;
		}
		if(requestURL.indexOf("register") > 0) {
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		HttpServletResponse response = requestContext.getResponse();
		//取cookie中的身份令牌
		String tokenFromCookie = authService.getTokenFromCookie(request);
		if(StringUtils.isEmpty(tokenFromCookie)) {
			//拒绝访问
			access_denied();
			return null;
		}
		//从header中取jwt
		String jwtFromHeader = authService.getJwtFromHeader(request);
		if(StringUtils.isEmpty(jwtFromHeader)) {
			access_denied();
			return null;
		}
		//从redis中取出jwt的过期时间
		long expire = authService.getExpire(tokenFromCookie);
		if(expire < 0) {
			access_denied();
			return null;
		}
		
		return null;
	}
	
	//拒绝访问
	private void access_denied() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletResponse response = requestContext.getResponse();
		requestContext.setSendZuulResponse(false);
		requestContext.setResponseStatusCode(200);
		ResponseResult responseResult = new ResponseResult(CommonCode.UNAUTHENTICATED);
		String jsonString = JSON.toJSONString(responseResult);
		requestContext.setResponseBody(jsonString);
		response.setContentType("application/json;charset=UTF-8");
	}
}
