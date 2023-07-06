package com.hrms.response.bean;

import java.util.Date;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeFetchResponse {

	private Integer id;

	private String empId;

	private String orgId;

	private Integer userId;

	private Date dateOfJoining;

	private String dateOfBirth;

	private Date dateOfleaving;

	private String reportingManagerId;

	private String reportingManager;

	private String emp_status_id;

	private String employmentStatus;

	private String businessunitName;

	private Integer departmentId;

	private String departmentName;

	private Integer jobtitle_id;

	private String gender;

	private String jobTitleName;

	private Integer position_id;

	private String designation;

	private Integer yearOfExp;

	private String holiday_group;

	private String holiday_group_name;

	private String prefix;

	private Long extensionNo;

	private String WorkTelephoneNo;

	private String faxNo;

	private Integer empRoleId;

	private String empRole;

	private String firstName;

	private String lastName;

	private String employeeName;

	private String email;

	private String contactnumber;

	private String backgroundchk_status;

	private String modeOfEntry;

	private int visaId;

	private String selectedDocumentsIds;

	private String other_modeofentry;

	private Date selecteddate;

	private String candidatereferredby;

	private String referer_name;

	private String profileImg;

	private String signature;

	private Integer createdby;

	private String createdby_name;

	private Integer modifiedBy;

	private Date modifieddate;

	private Date createddate;

	private int isactive;

	private Integer businessunitId;

	private String immManagerId;

	private String hrManagerId;

	private String immManagerName;

	private String hrManagerName;

	private String employeeStatus;

	private String ssnNumber;

	private String numberType;

}
