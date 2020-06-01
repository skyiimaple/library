package com.chuanshuke.search.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuanshuke.framework.domain.cms.TbBook;

public interface TbBookRepository extends JpaRepository<TbBook, Long> {
	
	public TbBook findByName(String name);

}
