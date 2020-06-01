package com.chuanshuke.ucenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuanshuke.framework.domain.ucenter.TbUser;

public interface TbUserRepository extends JpaRepository<TbUser, Long> {
	
	TbUser findByUsername(String username);
	
}
