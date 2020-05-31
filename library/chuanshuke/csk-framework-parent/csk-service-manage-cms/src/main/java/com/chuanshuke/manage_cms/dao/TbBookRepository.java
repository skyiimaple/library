package com.chuanshuke.manage_cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuanshuke.framework.domain.cms.TbBook;

public interface TbBookRepository extends JpaRepository<TbBook, Long> {
	TbBook findByName(String name);
}
