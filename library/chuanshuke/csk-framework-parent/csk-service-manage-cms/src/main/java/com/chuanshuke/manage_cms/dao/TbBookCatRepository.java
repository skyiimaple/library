package com.chuanshuke.manage_cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuanshuke.framework.domain.cms.TbBookCat;

public interface TbBookCatRepository extends JpaRepository<TbBookCat, Long> {
	
	TbBookCat findByName(String name);  //根据分类标签名称查询
	
	
}
