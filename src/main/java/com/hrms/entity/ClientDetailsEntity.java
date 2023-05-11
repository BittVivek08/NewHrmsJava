	package com.hrms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Client_details")
public class ClientDetailsEntity implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    

	@Column(name="client_id")
	private int clientid;


	private String clientname;

	private String email;

	private String phonenum;

	private String address;

	private String countryname;

	private String statename;

	private short is_active;
}
