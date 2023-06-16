package com.hrms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "main_organisationinfo")
public class OrganizationInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "organisationname")
	private String organizationName;

	@Column(name = "org_image")
	private String orgImage;

	@Column(name = "domain")
	private String domain;

	@Column(name = "website")
	private String website;

	@Column(name = "orgdescription")
	private String orgDescription;

	@Column(name = "totalemployees")
	private int totalEmployees;

	@Column(name = "registration_number")
	private String registrationNumber;

	@Column(name = "org_startdate")
	private Date orgStartDate;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "secondaryphone")
	private String secondaryPhone;

	@Column(name = "email")
	private String email;

	@Column(name = "secondaryemail")
	private String secondaryEmail;

	@Column(name = "faxnumber")
	private String faxNo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country", referencedColumnName = "id")
	private Tbl_CountriesEntity country;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state", referencedColumnName = "id")
	private Tbl_StatesEntity state;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city", referencedColumnName = "id")
	private Tbl_CitiesEntity city;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "address3")
	private String address3;

	@Column(name = "description")
	private String description;

	@Column(name = "orghead")
	private String organizationHead;

	@Column(name = "designation")
	private String designation;

	@Column(name = "company_FEIN_No")
	private String companyFEINNo;

	@Column(name = "NAICS_code")
	private String naicsCode;

	@Column(name = "stateOfIncorporation")
	private String stateOfIncorporation;

	@Column(name = "company_officers")
	private String companyOfficers;

	@Column(name = "headquarter_Address")
	private String headQuarterAddress;

	@Column(name = "branch_Address")
	private String branchAddress;

	@Column(name = "billing_Address")
	private String billingAddress;

	@JsonIgnore
	@Column(name = "createddate", updatable = false)
	private Timestamp created_Date;

	@JsonIgnore
	@Column(name = "createdby", updatable = false)
	private int createdBy;

	@JsonIgnore
	@Column(name = "modifiedby")
	private int modifiedBy;

	@JsonIgnore
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@JsonIgnore
	@Column(name = "isactive")
	private Short isActive;

}
