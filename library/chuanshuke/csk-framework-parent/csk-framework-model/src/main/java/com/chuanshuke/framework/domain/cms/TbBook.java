package com.chuanshuke.framework.domain.cms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "tb_book")
//@SolrDocument(collection = "chuanshuke")
public class TbBook implements Serializable {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Field
	private String name;  //书名
	@Field
	private String subtitle;  //图书子标题
	@Field
	private String author;  //原作者
	@Field
	private String translator;  //翻译作者
	@Field("ISBN")
	private String isbn;  //ISBN号
	@Field
	private String publisher;  //出版商
	@Column(name="total_page")  
	private Long totalPage;  //图书页数
	@Field
	private String image;  //图书封面图片
	@Field
	private Long bid;  //图书分类（标签）
	private int status;  //图书状态，1-正常，2-下架，3-删除 
	private Date created;  //创建时间
	private Date updated;  //更新时间

}
