package com.chuanshuke.auth.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserJwt extends User {
	
	private Long id;
	private String name;
//	private String userpic;
//	private String utype;
//	private String companyId;
	
	public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities ) {
		super(username, password, authorities);
	}
}
