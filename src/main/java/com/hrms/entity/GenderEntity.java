package com.hrms.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "main_gender")
public class GenderEntity {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;

	    @Column(name = "gendercode")
	    private String genderCode;

	    @Column(name = "gendername")
	    private String genderName;

	    @Column(name = "isactive")
	    private int isActive;

	    @Column(name = "createddate")
	    private Date createdDate;

	    @Column(name = "modifieddate")
	    private Date modifiedDate;


}
