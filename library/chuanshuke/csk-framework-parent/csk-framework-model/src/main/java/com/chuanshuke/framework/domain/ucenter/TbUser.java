package com.chuanshuke.framework.domain.ucenter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "tb_user")
@Data
@ToString
public class TbUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;  //账号
	private String name;  //昵称
	private String password;  //密码
	private String phone;  //手机号
	private String email;  //邮箱
	private Date created;
	private Date updated;
	private Integer usercategory;  //用户分类，普通用户：0， 管理员：1
}
