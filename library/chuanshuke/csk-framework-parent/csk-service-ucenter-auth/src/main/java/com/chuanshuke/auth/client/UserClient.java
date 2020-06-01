package com.chuanshuke.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chuanshuke.framework.client.CskServiceList;
import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.domain.ucenter.response.UcenterResult;

@FeignClient(value = CskServiceList.CSK_SERVICE_UCENTER)
public interface UserClient {
	@GetMapping("/ucenter/getuser")
	public UcenterResult getUser(@RequestParam("username") String username);
}
