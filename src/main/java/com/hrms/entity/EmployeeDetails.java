package com.hrms.entity;



import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="main_employees_summary")
public class EmployeeDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="emp_id", unique = true)
	private String empId;

	@Column(name = "user_id")
    private Integer userId;
	
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_joining")
    private Date dateOfJoining;
    
    @Column(name = "dob")
    private String dateOfBirth;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_leaving")
    private Date dateOfleaving;

    @Column(name = "reporting_manager")
    private String reportingManagerId;

    @Column(name = "reporting_manager_name")
    private String reportingManager;

    @Column(name = "emp_status_id")
    private String emp_status_id;

    @Column(name = "emp_status_name")
    private String employmentStatus;

    @Column(name = "businessunit_name")
    private String businessunitName;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "jobtitle_id")
    private Integer jobtitle_id;
    
    private String gender;

    @Column(name = "jobtitle_name")
    private String jobTitleName;

    @Column(name = "position_id")
    private Integer position_id;

    @Column(name = "position_name")
    private String designation;

    @Column(name = "years_exp")
    private Integer yearOfExp;

    @Column(name = "holiday_group")
    private String holiday_group;

    @Column(name = "holiday_group_name")
    private String holiday_group_name;

    @Column(name = "prefix_name")
    private String prefix;

    @Column(name = "extension_number")
    private Long extensionNo;

    @Column(name = "office_number")
    private String WorkTelephoneNo;

    @Column(name = "office_faxnumber")
    private String faxNo;
    
    @Column(name = "emprole")
    private Integer empRoleId;

    @Column(name = "emprole_name")
    private String empRole;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "userfullname")
    private String employeeName;

    @Column(name = "emailaddress")
    private String email;

    @Column(name = "contactnumber")
    private String contactnumber;

    @Column(name = "backgroundchk_status")
    private String backgroundchk_status;


    @Column(name = "modeofentry")
    private String modeOfEntry;

    @Column(name = "visa_id")
    private int visaId;

    @Column(name = "selected_documents")
    private String selectedDocumentsIds;

    @Column(name = "other_modeofentry")
    private String other_modeofentry;

    @Column(name = "selecteddate")
    private Date selecteddate;

    @Column(name = "candidatereferredby")
    private String candidatereferredby;

    @Column(name = "referer_name")
    private String referer_name;

    @Column(name = "profileimg")
    private String profileImg;

    @Column(name = "signature")
    private String signature;

    @Column(name = "createdby")
    private Integer createdby;

    @Column(name = "createdby_name")
    private String createdby_name;

    @Column(name = "modifiedby")
    private Integer modifiedBy;

    @Column(name = "modifieddate")
    private Date modifieddate;

    @Column(name = "createddate")
    private Date createddate;

    @Column(name = "isactive")
    private int isactive;

    @Column(name = "businessunit_id")
    private Integer businessunitId;
    
    @Column(name = "imm_manager_id")
    private String immManagerId;

    @Column(name = "hr_manager_id")
	private String hrManagerId;
    
    @Column(name = "imm_manager_name")
    private String immManagerName;

    @Column(name = "hr_manager_name")
	private String hrManagerName;
    
    @Column(name="employee_status")
    private String employeeStatus;
    
	private String ssnNumber;
	
	private String numberType;
	
	private String password;
	
	

	
}



