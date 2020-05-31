package com.chuanshuke.govern.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//@Component
public class LoginFilterTest extends ZuulFilter {
	
	private static final Logger Log = LoggerFactory.getLogger(LoginFilterTest.class);

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletResponse response = requestContext.getResponse();
		HttpServletRequest request = requestContext.getRequest();
		//取出头部信息Authorization
		String authorization = request.getHeader("Authorization");
		if(StringUtils.isEmpty(authorization)) {
			requestContext.setSendZuulResponse(false);  //拒绝访问
			requestContext.setResponseStatusCode(200);  //设置响应状态码
			ResponseResult unauthenticated = new ResponseResult(CommonCode.UNAUTHENTICATED);
			String jsonString = JSON.toJSONString(unauthenticated);
			requestContext.setResponseBody(jsonString);
			requestContext.getResponse().setContentType("application/json;charset=UTF-8");
			return null;
		}
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 2;  //int值来定义过滤器的执行顺序，数值越小优先级越高
	}

}
