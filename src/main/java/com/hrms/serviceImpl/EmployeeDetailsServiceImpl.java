package com.hrms.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrms.beans.CommonResponseBean;
import com.hrms.beans.ContactBean;
import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EmployeeDto;
import com.hrms.beans.EmployeeEducationDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.Businessunit;
import com.hrms.entity.ContactDetails;
import com.hrms.entity.Department;
import com.hrms.entity.EmpRole;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeInformation;
import com.hrms.entity.EmployeeSalaryDetails;
import com.hrms.repository.BusinessunitRepository;
import com.hrms.repository.ContactRepo;
import com.hrms.repository.DepartmentRepo;
import com.hrms.repository.EmpRoleRepo;
import com.hrms.repository.EmployeeEducationDetailsRepository;
import com.hrms.repository.EmployeeInformationRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.EmployeeSalaryDetailsRepo;
import com.hrms.request.bean.EmployeeSalaryRequestBean;
import com.hrms.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	Logger logging = LoggerFactory.getLogger(EmployeeDetailsServiceImpl.class);
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmployeeInformationRepository empInfRepo;

	@Autowired
	private EntityBeanResponse ebr;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BusinessunitRepository businessUnitRepository;

	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private EmpRoleRepo empRoleRepo;

	@Autowired
	private ContactRepo contactrepo;

	@Autowired
	private EmployeeSalaryDetailsRepo empSalRepo;

	@Autowired
	private ContactBean contactbean;

	@Autowired
	private EmployeeEducationDetailsBean empeducationbean;

	@Autowired
	private CommonResponseBean comnResBean;

	@Autowired
	private EmployeeEducationDetailsRepository empeducationrepo;

	@Override
	public EntityBeanResponse saveEmpDetails(EmployeeDetails employeeDetails) {
		String encode = this.passwordEncoder.encode(employeeDetails.getPassword());
		employeeDetails.setPassword(encode);
		
		
		List<String> reportingManagerList = empRepo
				.getReportingManagerByEmpId(employeeDetails.getReportingManagerId());

		employeeDetails.setReportingManager(reportingManagerList.get(0));

		List<String> hrManagerByHrManagerIdList = empRepo.getHrManagerByEmpId(employeeDetails.getHrManagerId());

		employeeDetails.setHrManagerName(hrManagerByHrManagerIdList.get(0));

		List<String> immManagerByImmManagerIdList = empRepo.getImmManagerByEmpId(employeeDetails.getImmManagerId());

		employeeDetails.setImmManagerName(immManagerByImmManagerIdList.get(0));
		
		Integer userId = empRepo.getMaxUserId()+1;
		employeeDetails.setUserId(userId);

		EmployeeDetails saved = empRepo.save(employeeDetails);
		EmployeeDetails findByEmail = empRepo.findByEmail(employeeDetails.getEmail());
		
		if (saved != null) {

			EmployeeDto empDto = new EmployeeDto();

			Optional<Businessunit> businessUnit = businessUnitRepository.findById(findByEmail.getBusinessunitId());

			Optional<Department> department = departmentRepo.findById(findByEmail.getDepartmentId());

			// Optional<EmpRole> empRole = empRoleRepo.findById(findByEmail.getEmpRoleId());

			empDto.setId(findByEmail.getId());
			empDto.setEmpId(findByEmail.getEmpId());
			empDto.setUserId(userId);
			empDto.setFirstName(findByEmail.getFirstName());
			empDto.setLastName(findByEmail.getLastName());
			empDto.setEmployeeName(findByEmail.getEmployeeName());
			empDto.setEmpRole(findByEmail.getEmpRole());
			empDto.setEmail(findByEmail.getEmail());
			empDto.setGender(findByEmail.getGender());
			empDto.setBackgroundchk_status(findByEmail.getBackgroundchk_status());
			empDto.setBusinessunitId(findByEmail.getBusinessunitId());
			empDto.setBusinessunitName(businessUnit.get().getName());
			// empDto.setBusinessunitName(findByEmail.getBusinessunitName());
			empDto.setCandidatereferredby(findByEmail.getCandidatereferredby());
			empDto.setContactnumber(findByEmail.getContactnumber());
			empDto.setCreatedby(findByEmail.getCreatedby());
			empDto.setCreatedby_name(findByEmail.getCreatedby_name());
			empDto.setCreateddate(findByEmail.getCreateddate());
			empDto.setDateOfJoining(findByEmail.getDateOfJoining());
			empDto.setDateOfleaving(findByEmail.getDateOfleaving());
			empDto.setDepartmentId(findByEmail.getDepartmentId());
			empDto.setDepartmentName(department.get().getDepName());
			// empDto.setDepartmentName(findByEmail.getDepartmentName());
			empDto.setDesignation(findByEmail.getDesignation());
			empDto.setEmp_status_id(findByEmail.getEmp_status_id());
			empDto.setEmployeeStatus(findByEmail.getEmployeeStatus());
			empDto.setEmploymentStatus(findByEmail.getEmploymentStatus());
			empDto.setExtensionNo(findByEmail.getExtensionNo());
			empDto.setFaxNo(findByEmail.getFaxNo());
			empDto.setHoliday_group(findByEmail.getHoliday_group());
			empDto.setHoliday_group_name(findByEmail.getHoliday_group_name());
			empDto.setHrManagerId(findByEmail.getEmpId());
			empDto.setHrManagerName(hrManagerByHrManagerIdList.get(0));
			empDto.setImmManagerId(findByEmail.getEmpId());
			empDto.setImmManagerName(immManagerByImmManagerIdList.get(0));
			empDto.setIsactive(findByEmail.getIsactive());
			empDto.setJobtitle_id(findByEmail.getJobtitle_id());
			empDto.setJobTitleName(findByEmail.getJobTitleName());
			empDto.setModeOfEntry(findByEmail.getModeOfEntry());
			empDto.setModifiedBy(findByEmail.getModifiedBy());
			empDto.setModifieddate(findByEmail.getModifieddate());
			empDto.setNumberType(findByEmail.getNumberType());
			empDto.setOther_modeofentry(findByEmail.getOther_modeofentry());
			empDto.setPosition_id(findByEmail.getPosition_id());
			empDto.setReferer_name(findByEmail.getReferer_name());
			// empDto.setReportingManager(empRole.get().getRoleName());
			empDto.setReportingManager(reportingManagerList.get(0));
			empDto.setReportingManagerId(findByEmail.getEmpId());
			empDto.setSelecteddate(findByEmail.getSelecteddate());
			empDto.setSelectedDocumentsIds(findByEmail.getSelectedDocumentsIds());
			empDto.setSignature(findByEmail.getSignature());
			empDto.setSsnNumber(findByEmail.getSsnNumber());
			empDto.setVisaId(findByEmail.getVisaId());
			empDto.setWorkTelephoneNo(findByEmail.getWorkTelephoneNo());
			empDto.setYearOfExp(findByEmail.getYearOfExp());
			ebr.setEmployeeDto(empDto);
			ebr.setMsg("Employee Details Saved Successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Details Saving Failed");
			ebr.setStatus(false);
			ebr.setEmployeeDto(null);
		}
		return ebr;

	}

	@Override
	public List<EmployeeDetails> getAllEmpDetails() {

		return empRepo.findAll();
	}

	@Override
	public EmployeeDetails getEmpById(Integer id) {
		Optional<EmployeeDetails> findById = empRepo.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public EntityBeanResponse updateEmpDetails(EmployeeDetails emplDetails, String empId) {
		Optional<EmployeeDetails> employeeDetails = empRepo.findById(empId);
		emplDetails.setPassword(employeeDetails.get().getPassword());

		EmployeeDetails update = empRepo.save(emplDetails);
		EmployeeDetails findByEmail = empRepo.findByEmail(employeeDetails.get().getEmail());
		if (update != null) {
			EmployeeDto empDto = new EmployeeDto();

			Optional<Businessunit> businessUnit = businessUnitRepository.findById(findByEmail.getBusinessunitId());

			Optional<Department> department = departmentRepo.findById(findByEmail.getDepartmentId());

			Optional<EmpRole> empRole = empRoleRepo.findById(findByEmail.getEmpRoleId());

			empDto.setId(findByEmail.getId());
			empDto.setEmpId(findByEmail.getEmpId());
			empDto.setUserId(findByEmail.getUserId());
			empDto.setFirstName(findByEmail.getFirstName());
			empDto.setLastName(findByEmail.getLastName());
			empDto.setEmployeeName(findByEmail.getEmployeeName());
			empDto.setEmpRole(findByEmail.getEmpRole());
			empDto.setEmail(findByEmail.getEmail());
			empDto.setGender(findByEmail.getGender());
			empDto.setBackgroundchk_status(findByEmail.getBackgroundchk_status());
			empDto.setBusinessunitId(findByEmail.getBusinessunitId());
			empDto.setBusinessunitName(businessUnit.get().getName());
			empDto.setCandidatereferredby(findByEmail.getCandidatereferredby());
			empDto.setContactnumber(findByEmail.getContactnumber());
			empDto.setCreatedby(findByEmail.getCreatedby());
			empDto.setCreatedby_name(findByEmail.getCreatedby_name());
			empDto.setCreateddate(findByEmail.getCreateddate());
			empDto.setDateOfJoining(findByEmail.getDateOfJoining());
			empDto.setDateOfleaving(findByEmail.getDateOfleaving());
			empDto.setDepartmentId(findByEmail.getDepartmentId());
			empDto.setDepartmentName(department.get().getDepName());
			empDto.setDesignation(findByEmail.getDesignation());
			empDto.setEmp_status_id(findByEmail.getEmp_status_id());
			empDto.setEmployeeName(findByEmail.getEmployeeName());
			empDto.setEmployeeStatus(findByEmail.getEmployeeStatus());
			empDto.setEmploymentStatus(findByEmail.getEmploymentStatus());
			empDto.setExtensionNo(findByEmail.getExtensionNo());
			empDto.setFaxNo(findByEmail.getFaxNo());
			empDto.setHoliday_group(findByEmail.getHoliday_group());
			empDto.setHoliday_group_name(findByEmail.getHoliday_group_name());
			empDto.setHrManagerId(findByEmail.getHrManagerId());
			empDto.setHrManagerName(findByEmail.getHrManagerName());
			empDto.setImmManagerId(findByEmail.getImmManagerId());
			empDto.setImmManagerName(findByEmail.getImmManagerName());
			empDto.setIsactive(findByEmail.getIsactive());
			empDto.setJobtitle_id(findByEmail.getJobtitle_id());
			empDto.setJobTitleName(findByEmail.getJobTitleName());
			empDto.setModeOfEntry(findByEmail.getModeOfEntry());
			empDto.setModifiedBy(findByEmail.getModifiedBy());
			empDto.setModifieddate(findByEmail.getModifieddate());
			empDto.setNumberType(findByEmail.getNumberType());
			empDto.setOther_modeofentry(findByEmail.getOther_modeofentry());
			empDto.setPosition_id(findByEmail.getPosition_id());
			empDto.setReferer_name(findByEmail.getReferer_name());
			empDto.setReportingManager(empRole.get().getRoleName());
			empDto.setReportingManagerId(findByEmail.getReportingManagerId());
			empDto.setSelecteddate(findByEmail.getSelecteddate());
			empDto.setSelectedDocumentsIds(findByEmail.getSelectedDocumentsIds());
			empDto.setSignature(findByEmail.getSignature());
			empDto.setSsnNumber(findByEmail.getSsnNumber());
			empDto.setVisaId(findByEmail.getVisaId());
			empDto.setWorkTelephoneNo(findByEmail.getWorkTelephoneNo());
			empDto.setYearOfExp(findByEmail.getYearOfExp());
			ebr.setEmployeeDto(empDto);
			ebr.setMsg("Employee Details Updated Successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Details Updation Failed");
			ebr.setStatus(false);
		}
		return ebr;
	}

	@Override
	public List<EmpBirthResponse> findBirthdayDetails() {

		List<EmployeeDetails> empList = empRepo.findAll();
		List<EmployeeDetails> empListBirhdays = new ArrayList<>();
		List<EmpBirthResponse> lis = new ArrayList<>();
		LocalDate today = LocalDate.now();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			for (EmployeeDetails emp : empList) {

				Date dob = sdf.parse(emp.getDateOfBirth());

				if (dob.getDate() == today.getDayOfMonth() && dob.getMonth() + 1 == today.getMonthValue()) {

					EmpBirthResponse res = new EmpBirthResponse();
					res.setFirstname(emp.getFirstName());
					res.setLastname(emp.getLastName());
					res.setDob(emp.getDateOfBirth());
					res.setEmpid(emp.getEmpId());
					lis.add(res);
					empListBirhdays.add(emp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return lis;
	}

	@Override
	public EntityBeanResponse loginEmployee(LoginDto loginDto) {

		EmployeeDetails employee1 = empRepo.findByEmail(loginDto.getEmail());

		if (employee1 != null) {
			String password = loginDto.getPassword();
			String encodedPassword = employee1.getPassword();
			boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {
				Optional<EmployeeDetails> employee = empRepo.findByEmailAndPassword(loginDto.getEmail(),
						encodedPassword);
				EmployeeDto empDto = new EmployeeDto();
				empDto.setId(employee1.getId());
				empDto.setEmpId(employee1.getEmpId());
				empDto.setUserId(employee1.getUserId());
				empDto.setFirstName(employee1.getFirstName());
				empDto.setLastName(employee1.getLastName());
				empDto.setEmployeeName(employee1.getEmployeeName());
				empDto.setEmpRole(employee1.getEmpRole());
				empDto.setEmail(employee1.getEmail());
				empDto.setBackgroundchk_status(employee1.getBackgroundchk_status());
				empDto.setBusinessunitId(employee1.getBusinessunitId());
				empDto.setBusinessunitName(employee1.getBusinessunitName());
				empDto.setCandidatereferredby(employee1.getCandidatereferredby());
				empDto.setContactnumber(employee1.getContactnumber());
				empDto.setCreatedby(employee1.getCreatedby());
				empDto.setCreatedby_name(employee1.getCreatedby_name());
				empDto.setCreateddate(employee1.getCreateddate());
				empDto.setDateOfJoining(employee1.getDateOfJoining());
				empDto.setDateOfleaving(employee1.getDateOfleaving());
				empDto.setDepartmentId(employee1.getDepartmentId());
				empDto.setDepartmentName(employee1.getDepartmentName());
				empDto.setDesignation(employee1.getDesignation());
				empDto.setEmp_status_id(employee1.getEmp_status_id());
				empDto.setEmployeeName(employee1.getEmployeeName());
				empDto.setEmployeeStatus(employee1.getEmployeeStatus());
				empDto.setEmploymentStatus(employee1.getEmploymentStatus());
				empDto.setExtensionNo(employee1.getExtensionNo());
				empDto.setFaxNo(employee1.getFaxNo());
				empDto.setHoliday_group(employee1.getHoliday_group());
				empDto.setHoliday_group_name(employee1.getHoliday_group_name());
				empDto.setHrManagerId(employee1.getHrManagerId());
				empDto.setHrManagerName(employee1.getHrManagerName());
				empDto.setImmManagerId(employee1.getImmManagerId());
				empDto.setImmManagerName(employee1.getImmManagerName());
				empDto.setIsactive(employee1.getIsactive());
				empDto.setJobtitle_id(employee1.getJobtitle_id());
				empDto.setJobTitleName(employee1.getJobTitleName());
				empDto.setModeOfEntry(employee1.getModeOfEntry());
				empDto.setModifiedBy(employee1.getModifiedBy());
				empDto.setModifieddate(employee1.getModifieddate());
				empDto.setNumberType(employee1.getNumberType());
				empDto.setOther_modeofentry(employee1.getOther_modeofentry());
				empDto.setPosition_id(employee1.getPosition_id());
				empDto.setReferer_name(employee1.getReferer_name());
				empDto.setReportingManager(employee1.getReportingManager());
				empDto.setReportingManagerId(employee1.getReportingManagerId());
				empDto.setSelecteddate(employee1.getSelecteddate());
				empDto.setSelectedDocumentsIds(employee1.getSelectedDocumentsIds());
				empDto.setSignature(employee1.getSignature());
				empDto.setSsnNumber(employee1.getSsnNumber());
				empDto.setVisaId(employee1.getVisaId());
				empDto.setWorkTelephoneNo(employee1.getWorkTelephoneNo());
				empDto.setYearOfExp(employee1.getYearOfExp());
				ebr.setEmployeeDto(empDto);
				if (employee.isPresent()) {
					ebr.setStatus(true);
					ebr.setMsg("Login Success");
				} else {
					ebr.setStatus(false);
					ebr.setMsg("Login Failed");
					ebr.setEmployeeDto(null);
				}

			} else {
				ebr.setStatus(false);
				ebr.setMsg("password not matched");
				ebr.setEmployeeDto(null);
			}
		} else {
			ebr.setStatus(false);
			ebr.setMsg("Email Does Not Exits");
			ebr.setEmployeeDto(null);
		}
		return ebr;
	}

	@Override
	public EmployeeDto loginViaJWT(String email) {

		EmployeeDetails employee1 = empRepo.findByEmail(email);

		EmployeeDto empDto = new EmployeeDto();
		empDto.setId(employee1.getId());
		empDto.setEmpId(employee1.getEmpId());
		empDto.setUserId(employee1.getUserId());
		empDto.setFirstName(employee1.getFirstName());
		empDto.setLastName(employee1.getLastName());
		empDto.setEmployeeName(employee1.getEmployeeName());
		empDto.setEmpRole(employee1.getEmpRole());
		empDto.setEmail(employee1.getEmail());
		empDto.setBackgroundchk_status(employee1.getBackgroundchk_status());
		empDto.setBusinessunitId(employee1.getBusinessunitId());
		empDto.setBusinessunitName(employee1.getBusinessunitName());
		empDto.setCandidatereferredby(employee1.getCandidatereferredby());
		empDto.setContactnumber(employee1.getContactnumber());
		empDto.setCreatedby(employee1.getCreatedby());
		empDto.setCreatedby_name(employee1.getCreatedby_name());
		empDto.setCreateddate(employee1.getCreateddate());
		empDto.setDateOfJoining(employee1.getDateOfJoining());
		empDto.setDateOfleaving(employee1.getDateOfleaving());
		empDto.setDepartmentId(employee1.getDepartmentId());
		empDto.setDepartmentName(employee1.getDepartmentName());
		empDto.setDesignation(employee1.getDesignation());
		empDto.setEmp_status_id(employee1.getEmp_status_id());
		empDto.setEmployeeName(employee1.getEmployeeName());
		empDto.setEmployeeStatus(employee1.getEmployeeStatus());
		empDto.setEmploymentStatus(employee1.getEmploymentStatus());
		empDto.setExtensionNo(employee1.getExtensionNo());
		empDto.setFaxNo(employee1.getFaxNo());
		empDto.setHoliday_group(employee1.getHoliday_group());
		empDto.setHoliday_group_name(employee1.getHoliday_group_name());
		empDto.setHrManagerId(employee1.getHrManagerId());
		empDto.setHrManagerName(employee1.getHrManagerName());
		empDto.setImmManagerId(employee1.getImmManagerId());
		empDto.setImmManagerName(employee1.getImmManagerName());
		empDto.setIsactive(employee1.getIsactive());
		empDto.setJobtitle_id(employee1.getJobtitle_id());
		empDto.setJobTitleName(employee1.getJobTitleName());
		empDto.setModeOfEntry(employee1.getModeOfEntry());
		empDto.setModifiedBy(employee1.getModifiedBy());
		empDto.setModifieddate(employee1.getModifieddate());
		empDto.setNumberType(employee1.getNumberType());
		empDto.setOther_modeofentry(employee1.getOther_modeofentry());
		empDto.setPosition_id(employee1.getPosition_id());
		empDto.setReferer_name(employee1.getReferer_name());
		empDto.setReportingManager(employee1.getReportingManager());
		empDto.setReportingManagerId(employee1.getReportingManagerId());
		empDto.setSelecteddate(employee1.getSelecteddate());
		empDto.setSelectedDocumentsIds(employee1.getSelectedDocumentsIds());
		empDto.setSignature(employee1.getSignature());
		empDto.setSsnNumber(employee1.getSsnNumber());
		empDto.setVisaId(employee1.getVisaId());
		empDto.setWorkTelephoneNo(employee1.getWorkTelephoneNo());
		empDto.setYearOfExp(employee1.getYearOfExp());

		return empDto;
	}

	public EntityBeanResponse saveEmployeeInformation(String empId, EmployeeInformation empInformation) {

		EmployeeDetails employeeDetails = empRepo.findByEmpId(empId);
		empInformation.setEmployeeDetails(employeeDetails);

		empInformation.setEmployeeDetails(employeeDetails);
		EmployeeInformation empInfo = empInfRepo.save(empInformation);

		if (empInfo != null) {
			ebr.setMsg("Employee Information saved successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Information Saving Failed !");
			ebr.setStatus(false);
		}

		return ebr;
	}

	@Override
	public EntityBeanResponse updateEmployeeInformation(EmployeeInformation entity) {

		EmployeeDetails emp = empRepo.findByEmpId(entity.getEmployeeDetails().getEmpId());
		entity.setEmployeeDetails(emp);

		EmployeeInformation updated = empInfRepo.save(entity);

		if (updated != null) {
			ebr.setMsg("Employee Information Updated Successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Information Updation Failed");
			ebr.setStatus(false);
		}

		return ebr;
	}

	@Override
	public EmployeeInformation getEmpInfoById(Integer id) {
		Optional<EmployeeInformation> findById = empInfRepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public ContactBean saveContactdata(String empId, ContactDetails details) {
		logging.info("entered into savecontactdata method of service imlementation class");
		EmployeeDetails employeedetails = empRepo.findByEmpId(empId);
		details.setEmployeedetails(employeedetails);
		ContactDetails contactdetails = contactrepo.save(details);

		if (contactdetails != null) {
			contactbean.setMessage("contact details save successfully");
			contactbean.setStatus(true);
		} else {
			contactbean.setMessage("contact details not saved");
			contactbean.setStatus(false);
		}
		return contactbean;
	}

	@Override
	public List<ContactDetails> getContactdata() {
		logging.info("entered into getcontactdata method of service imlementation class");
		return contactrepo.findAll();
	}

	@Override
	public ContactDetails updateContact(ContactDetails entity,String empId) {
		logging.info("entered into updatecontact method of service imlementation class");
		EmployeeDetails emp = empRepo.findByEmpId(empId);
		entity.setEmployeedetails(emp);

		return contactrepo.save(entity);

	}

	public ContactDetails getcontactDetails(int id) {
		logging.info("entered into getcontactDetails method of service imlementation class");
		Optional<ContactDetails> contact = contactrepo.findById(id);
		if (contact.isPresent()) {
			return contact.get();

		}
		return null;
	}

	@Override
	public EmployeeEducationDetailsBean saveEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails,
			String empId) {

		// EmployeeDetails
		// employeeDetails=empRepo.findByEmpId(empeducationdetails.getEmployeeDetails().getEmpId());

		EmployeeDetails employeeDetails = empRepo.findByEmpId(empId);

		empeducationdetails.setEmployeeDetails(employeeDetails);

		EmployeeEducationDetails empeducation = empeducationrepo.save(empeducationdetails);

		if (empeducation != null) {
			empeducationbean.setMessage("EmployeeEducation details saved successfully");
			empeducationbean.setStatus(true);
		} else {
			empeducationbean.setMessage("EmployeeEducation details Saving Failed !");
			empeducationbean.setStatus(false);
		}

		return empeducationbean;

	}

	@Override
	public EmployeeEducationDetailsBean updateEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails,String empId) {

		EmployeeDetails employeeDetails = empRepo.findByEmpId(empId);
		// EmployeeDetails employeeDetails=empRepo.findByEmpId(empId);
		empeducationdetails.setEmployeeDetails(employeeDetails);

		EmployeeEducationDetails empeducation = empeducationrepo.save(empeducationdetails);

		if (empeducation != null) {
			empeducationbean.setMessage("EmployeeEducation details updated successfully");
			empeducationbean.setStatus(true);
		} else {
			empeducationbean.setMessage("EmployeeEducation details Failed !");
			empeducationbean.setStatus(false);
		}

		return empeducationbean;
	}

	@Override
	public EmployeeEducationDetails getEmpeducationdetalsById(Integer id) {

		Optional<EmployeeEducationDetails> empeducation = empeducationrepo.findById(id);
		if (empeducation.isPresent()) {
			return empeducation.get();
		}
		return null;
	}

	@Override
	public List<EmployeeEducationDetails> getAllEmpeducationdetails() {

		return empeducationrepo.findAll();
	}

	@Override
	public CommonResponseBean saveSalaryDetails(String empId, EmployeeSalaryRequestBean empSalReqBean) {
		EmployeeDetails employeeDetails = empRepo.findByEmpId(empId);
		EmployeeSalaryDetails entity = new EmployeeSalaryDetails();
		entity.setEmployeeDetails(employeeDetails);

		entity.setSalary(empSalReqBean.getSalary());
		entity.setAccountClassTypeId(empSalReqBean.getAccountClassTypeId());
		entity.setAccountHolderName(empSalReqBean.getAccountHolderName());
		entity.setAccountNumber(empSalReqBean.getAccountNumber());
		entity.setAccountHoldingSince(empSalReqBean.getAccountHoldingSince());
		entity.setBankName(empSalReqBean.getBankName());
		entity.setCurrencyId(empSalReqBean.getCurrencyId());
		entity.setCreatedDate(empSalReqBean.getCreatedDate());
		entity.setEffectiveStartDate(empSalReqBean.getEffectiveStartDate());
		entity.setEffectiveEndDate(empSalReqBean.getEffectiveEndDate());
		entity.setIfscCode(empSalReqBean.getIfscCode());
		entity.setIsActive(empSalReqBean.getIsActive());
		entity.setModifiedDate(empSalReqBean.getModifiedDate());

		EmployeeSalaryDetails saved = empSalRepo.save(entity);

		if (saved != null) {
			comnResBean.setMsg("Salary Details Saved Successfully");
			comnResBean.setStatus(true);
		} else {
			comnResBean.setMsg("Salary Details Saving Failed!!");
			comnResBean.setStatus(false);
		}

		return comnResBean;
	}

	@Override
	public CommonResponseBean updateSalaryDetails(EmployeeSalaryDetails empSalDetails,String empId) {

//		Optional<EmployeeSalaryDetails> empSalOpt = empSalRepo.findById(empSalDetails.getId());

		EmployeeDetails findByEmpId = empRepo.findByEmpId(empId);
		empSalDetails.setEmployeeDetails(findByEmpId);

		EmployeeSalaryDetails updated = empSalRepo.save(empSalDetails);

		if (updated != null) {
			comnResBean.setMsg("Salary Details Updated Successfully");
			comnResBean.setStatus(true);
		} else {

			comnResBean.setMsg("Salary Details Updation Failed");

			comnResBean.setStatus(false);
		}

		return comnResBean;
	}

	@Override
	public EmployeeSalaryDetails getEmpSalaryById(Integer id) {

		Optional<EmployeeSalaryDetails> findById = empSalRepo.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public EmployeeDetails getEmpByEmpId(String empId) {
		
		EmployeeDetails findById = empRepo.findByEmpId(empId);

		if (findById != null) {
			return findById;
		}
		return null;

	}

	@Override
	public EmployeeInformation getEmpInfoByEmpId(String empId) {
		
		Optional<EmployeeInformation> findById = empInfRepo.findByEmpId(empId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public ContactDetails getcontactDetails(String empId) {
		
		Optional<ContactDetails> contact = contactrepo.findByEmpId(empId);
		if (contact.isPresent()) {
			return contact.get();

		}
		return null;

	}

	@Override
	public EmployeeEducationDetails getEmpeducationdetalsByEmpId(String empId) {
		
		Optional<EmployeeEducationDetails> empeducation = empeducationrepo.findByEmpId(empId);
		if (empeducation.isPresent()) {
			return empeducation.get();
		}
		return null;
	}

	@Override
	public EmployeeSalaryDetails getEmpSalaryByEmpId(String empId) {
		
		List<EmployeeSalaryDetails> findById = empSalRepo.findByEmpId(empId);
 for(EmployeeSalaryDetails id:findById) {
		if (id!=null) {
			return findById.get(0);
		}
	}
		return null;
	}
}