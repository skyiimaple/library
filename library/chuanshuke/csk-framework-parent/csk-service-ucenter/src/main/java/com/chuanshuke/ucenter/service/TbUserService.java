package com.chuanshuke.ucenter.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.domain.ucenter.response.UcenterCode;
import com.chuanshuke.framework.domain.ucenter.response.UcenterResult;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.framework.utils.BCryptUtil;
import com.chuanshuke.ucenter.dao.TbUserRepository;

@Service
public class TbUserService {
	
	@Autowired
	private TbUserRepository tbUserRepository;
	
	public UcenterResult findByUsername(String username) {
		TbUser user = tbUserRepository.findByUsername(username);
		if(user == null) {
			return new UcenterResult(UcenterCode.UCENTER_ACCOUNT_NOTEXISTS, null);
		}
		return new UcenterResult(CommonCode.SUCCESS, user);
	}
	
	public ResponseResult insert(TbUser user) {
		String name = user.getName();
		TbUser one = tbUserRepository.findByUsername(name);
		if(one != null) {
			return new ResponseResult(UcenterCode.UCENTER_USERNAME_ISEXIST);
		}
		String encode = BCryptUtil.encode(user.getPassword());
		user.setPassword(encode);
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setUsercategory(0);
		tbUserRepository.save(user);
		return new ResponseResult(CommonCode.SUCCESS);
	}
	
	
}
