package com.hrms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_menu")
public class MenMenu1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "menuName")
	private String menuName;

	@Column(name = "parent")
	private Integer parent;

	@Column(name = "url")
	private String urls;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "imageUrl")
	private String imgUrl;


}
