package com.chuanshuke.framework.domain.portal;

//import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BookInfo {
	private Long id;
	private String name;  
	private String subtitle;  
	private String isbn;
	private String author;
	private String translator;
	private String publisher;
	private String image;
//	@Column(name="total_page")
	private String totalPage;
//	@Column(name="author_profile")
	private String authorProfile;
//	@Column(name = "content_desc")
	private String contentDesc;
	private String catalogue;
	private String category;
}
