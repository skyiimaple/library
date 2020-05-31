package com.chuanshuke.framework.domain.search;

import java.io.Serializable;
import java.util.List;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.model.response.ResponseResult;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchResult extends ResponseResult implements Serializable{
	private long recordCount;  //搜索条数
	private List<TbBook> list;	
	
}
