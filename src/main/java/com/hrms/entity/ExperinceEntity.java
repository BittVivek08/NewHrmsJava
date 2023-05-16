package com.hrms.entity;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="main_empexperiancedetails")
@Data
public class ExperinceEntity {
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    @OneToOne(cascade=CascadeType.ALL )
		@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
		private EmployeeDetails employeedetails;

	    @Column(name = "comp_name")
	    private String companyName;

	    @Column(name = "comp_website")
	    private String companyWebsite;

	    @Column(name = "designation")
	    private String designation;

	    @Column(name = "from_date")
	    private Date fromDate;

	    @Column(name = "to_date")
	    private Date toDate;

	    @Column(name = "reason_for_leaving")
	    private String reasonForLeaving;

	    @Column(name = "reference_name")
	    private String referrerName;

	    @Column(name = "reference_contact")
	    private String referrerContact;

	    @Column(name = "reference_email")
	    private String referrerEmail;

	    @Column(name = "isactive")
	    private int isActive;

	    @Column(name = "createddate")
	    private Date createdDate;

	    @Column(name = "modifieddate")
	    private Date modifiedDate;

	    @Column(name = "createdby")
	    private int createdBy;

	    @Column(name = "modifiedby")
	    private int modifiedBy;
	
}
