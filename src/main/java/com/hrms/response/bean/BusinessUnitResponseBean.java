package com.hrms.response.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;



@Data
@Component
public class BusinessUnitResponseBean {

	private int id;

	private int bid;

	private String orgId;

	private String name;

	private String code;

	private String description;

	private String startdate;

	private String country;

	private String state;

	private String city;

	private String address1;

	private String address2;

	private String createdby;

	private String modifiedBy;

	private Date modifiedDate;

	private int isactive;

	private Date createddate;

	private Integer timezone;

}
