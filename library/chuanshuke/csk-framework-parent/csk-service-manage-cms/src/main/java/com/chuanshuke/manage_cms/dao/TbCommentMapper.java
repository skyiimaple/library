package com.chuanshuke.manage_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chuanshuke.framework.domain.portal.CommentInfo;

@Mapper
@Repository
public interface TbCommentMapper {
	
	@Select("SELECT u.`name` nick_name, c.`score`, c.`content` " + 
			"  FROM tb_comment AS c " + 
			"  JOIN tb_user AS u " + 
			"    ON c.`uid` = u.`id` " + 
			"  JOIN tb_book AS b " + 
			"    ON c.`bid` = b.`id` " + 
			"WHERE c.`bid` = #{bid} ")
	public List<CommentInfo> listCommentInBookDetail(@Param("bid") Long bid);

}
