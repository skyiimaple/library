package com.chuanshuke.framework.domain.cms.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 接收图书查询的条件
 * @author 15211
 *
 */
@Data
@ToString
@NoArgsConstructor
public class QueryBookRequest {
		
	@ApiModelProperty("书名")
	private String name;
	
	@ApiModelProperty("图书副标题")
	private String subtitle;
	
	@ApiModelProperty("作者")
	private String author;
	
	@ApiModelProperty("ISBN")
	private String isbn;
	
	@ApiModelProperty("出版社")
	private String publisher;
	
	@ApiModelProperty("翻译")
	private String translator;
	
	@ApiModelProperty("分类")
	private Long bid;
	
	

}
