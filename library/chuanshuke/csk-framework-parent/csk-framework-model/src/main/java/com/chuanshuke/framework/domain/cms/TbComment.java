package com.chuanshuke.framework.domain.cms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "tb_comment")
@ToString
@NoArgsConstructor
public class TbComment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  //主键
	private Long uid;  //用户
	private Long bid;  //图书
	private Integer score;  //得分
	private String content;  //内容
	private Date created;
	private Date updated;
}
