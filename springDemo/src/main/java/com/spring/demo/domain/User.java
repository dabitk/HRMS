package com.spring.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long empno;
	
	@Column(length=20, unique=true)
	private String username;
	
	@Column(nullable=false)
	private String passwd;
	
	@Column(length=6)
	private String nameKor;
	
	@Column
	private Date birthday;
	
	@Column
	private Date createDate;
	
	@Column(length=13)
	private String tel;
	
	@Column(length=50)
	private String email;
	
	@Column
	private boolean sex;
}
