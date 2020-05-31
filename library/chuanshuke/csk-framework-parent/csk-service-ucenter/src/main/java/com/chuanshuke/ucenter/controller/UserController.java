package com.chuanshuke.ucenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuanshuke.api.ucenter.UserControllerApi;
import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.domain.ucenter.response.UcenterResult;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.ucenter.service.TbUserService;

@RestController
@RequestMapping("/ucenter")
public class UserController implements UserControllerApi {
	
	@Autowired
	private TbUserService tbUserService;
	
	@GetMapping("/getuser")
	@Override
	public UcenterResult getUser(@RequestParam("username")String username) {
		return tbUserService.findByUsername(username);
	}
	
	@PostMapping("/register")
	public ResponseResult insertUser(@RequestBody TbUser user) {
		return tbUserService.insert(user);
	}
	


}
