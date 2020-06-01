package com.chuanshuke.protal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chuanshuke.framework.domain.portal.BookInfo;

@Mapper
@Repository
public interface BookInfoMapper {
	
	@Select("SELECT b.`id`, b.`name`, b.`subtitle`, b.`isbn`, b.`author`, b.`translator`, b.`publisher`, b.`image`,"+
			"  b.`total_page`, d.`author_profile`, d.`content_desc`, d.`catalogue`, c.`name` AS category " + 
			"FROM tb_book AS b " + 
			"  JOIN tb_book_desc AS d " + 
			"    ON b.`id` = d.`book_id` " + 
			"  JOIN tb_book_cat  AS c " + 
			"    ON b.`bid` = c.`id`" + 
			"WHERE b.`id` = #{name}")
	List<BookInfo> getBookInfoById(@Param("name") String name);

}
