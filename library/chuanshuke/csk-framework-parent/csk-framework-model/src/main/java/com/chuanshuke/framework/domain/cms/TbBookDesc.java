package com.chuanshuke.framework.domain.cms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "tb_book_desc")
public class TbBookDesc implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "book_id")
	private Long bookId;
	@Column(name = "author_profile")
	private String authorProfile;  //作者简介
	@Column(name = "content_desc")
	private String contentDesc;  //内容简介
	private String catalogue;  //目录
	private Date created;
	private Date updated;

}
