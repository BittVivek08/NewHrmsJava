package com.hrms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.protobuf.Timestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "business_unit" )
public class Businessunit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int bid;
	
	private int service_desk_flag;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private String startdate;
	
	private String country;
	
	private String state;
	
	private String city;
	
	
	private String address1;
	
	
	
	 private String address2;
	 
	
	private String address3;
	
	
    
	private String unitname;
	
	
	private String unitcode;
	
	
   
    private Timestamp createddate;

    
   
    private int createdby;

    
    @Column(name = "modifiedby")
    private int modifiedBy;
    
       @Column(name = "modifieddate")
    private Timestamp modifiedDate;
       


    private int isactive;
    
     
    private Integer timezone;
 


	

}
