package com.chuanshuke.framework.domain.cms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "tb_book_cat")
public class TbBookCat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "parent_id")
	private Long parentId;
	private String name;  //分类标签名
	private Integer status;  //状态。可选值：1（正常）,2(删除)
	@Column(name = "sort_order")
	private Integer sortOrder;  //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围：大于0的整数
	@Column(name = "is_parent")
	private Integer isParent;  //该类目是否为父类目，1为ture，0为false
	private Date created;
	private Date updated;
	
}
