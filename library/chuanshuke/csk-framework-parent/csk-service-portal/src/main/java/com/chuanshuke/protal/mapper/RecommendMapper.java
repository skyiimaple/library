package com.chuanshuke.protal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.portal.RecommendData;

@Mapper
@Repository
public interface RecommendMapper {
	
	@Select("SELECT b.`name` book, u.`username` USER " + 
			"FROM tb_comment AS c " + 
			"JOIN tb_book AS b " + 
			"  ON b.`id` = c.`bid` " + 
			"JOIN tb_user AS u " + 
			"  ON c.`uid` = u.`id` " + 
			"order by b.`bid`")
	List<RecommendData> getData();
	
	@Select("SELECT COUNT(c.`bid`)AS num, b.* " + 
			"FROM tb_comment AS c " + 
			"JOIN tb_book b " + 
			"  ON c.`bid` = b.`id` " + 
			"GROUP BY c.`bid` " + 
			"ORDER BY num DESC " + 
			"LIMIT 60")
	List<TbBook> getHotTop();

}
