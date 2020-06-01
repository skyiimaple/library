package com.chuanshuke.manage_cms.dao;

import org.apache.ibatis.annotations.Mapper;

import com.chuanshuke.framework.domain.cms.ext.TbBookCatNode;

@Mapper
public interface TbBookCatMapper {
	
	//分类叶子结构
	public TbBookCatNode selectList();

}
