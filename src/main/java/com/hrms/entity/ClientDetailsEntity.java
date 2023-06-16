	package com.hrms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tm_clients")
public class ClientDetailsEntity implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    

	/*
	 * @Column(name="client_id") private String clientid;
	 */

	@Column(name = "client_name")
	private String clientName;


	private String email;

	@Column(name = "phone_no")
	private String phoneNo;
	
	private String poc;

	private String address;

	@Column(name = "country_id")
	private int countryId;
	
	@Column(name = "state_id")
	private int stateId;
	
	@Column(name = "fax")
	private String fax;


	@Column(name = "created_by")
	private int createdBy;
	
	
	@Column(name = "modified_by")
	private int modifiedBy;
	
	@Column(name = "created")
	private LocalDateTime createdDate;

	
	@Column(name = "modified")
	private LocalDateTime modifiedDate;
	

	private byte is_active;
}
