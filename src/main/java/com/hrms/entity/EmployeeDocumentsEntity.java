package com.hrms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "main_employeedocuments")
public class EmployeeDocumentsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@JsonIgnore
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "documentName")
	private String documentName;

	@JsonIgnore
	@Column(name = "attachments")
	private String DocumentPath;

	@Lob
    private byte[] data;
	
	@ManyToOne
	@JoinColumn(name = "emp_Id", referencedColumnName = "emp_id")
	private EmployeeDetails empdetails;
	
	@Column(name ="fileName")
	private String fileName;

	@Transient
	private String documentUrl;

	@Column(name = "documentId")
	private int documentId;

	@Column(name = "fromDate")
	private Date fromDate;

	@Column(name = "toDate")
	private Date toDate;

	@Column(name = "isExpires")
	private int isExpires;


	@Column(name = "createdby")
	private int createdBy;
	
	@Column(name="isActive")
	private int isactive;
	
	@JsonIgnore
	@Column(name = "modifiedby")
	private int modifiedBy;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "createddate")
	private Date createdDate;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "modifieddate")
	private Date modifiedDate;

}
