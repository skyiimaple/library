package com.chuanshuke.protal.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chuanshuke.framework.domain.cms.TbBook;

public interface RecommendBookRepository extends JpaRepository<TbBook, Long>{
	
	List<TbBook> findByNameIn(List<String> Names);
	
}
