package com.hrms.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrms.beans.ContactBean;
import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EmployeeDto;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.ContactDetails;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeInformation;
import com.hrms.repository.ContactRepo;
import com.hrms.repository.EmployeeInformationRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.entity.EmployeeSalaryDetails;
import com.hrms.repository.EmployeeSalaryRepository;
import com.hrms.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmployeeInformationRepository empInfRepo;

	@Autowired
	private EntityBeanResponse ebr;

	@Autowired
	private EmployeeSalaryRepository empSalRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ContactRepo contactrepo;
	
	@Autowired
	private ContactBean contactbean;

	@Override
	public EntityBeanResponse saveEmpDetails(EmployeeDetails employeeDetails) {
		String encode = this.passwordEncoder.encode(employeeDetails.getPassword());
		employeeDetails.setPassword(encode);

		EmployeeDetails saved = empRepo.save(employeeDetails);

		// parent + child record data
		// EmployeeDetails empDetails = new EmployeeDetails();

		// empDetails.getEmpSalDetails().setDetails(empDetails);
		// only save parent entity class

		// EmployeeSalaryDetails sal = new
		// EmployeeSalaryDetails(employeeDetails.getChild());
		// empDetails.setEmpSalDetails(sal);

		if (saved != null) {
			ebr.setMsg("Employee Details Saved Successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Details Saving Failed");
			ebr.setStatus(false);
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
	public EntityBeanResponse updateEmpDetails(EmployeeDetails employeeDetails) {

		EmployeeDetails update = empRepo.save(employeeDetails);
		if (update != null) {
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
				empDto.setFirstName(employee1.getFirstName());
				empDto.setLastName(employee1.getLastName());
				empDto.setEmpRole(employee1.getEmpRole());
				empDto.setEmail(employee1.getEmail());
				empDto.setGender(employee1.getGender());
				empDto.setDateOfBirth(employee1.getDateOfBirth());
				empDto.setQualification(employee1.getQualification());
				empDto.setMobileNumber(employee1.getMobileNumber());
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
	public EntityBeanResponse saveSalaryDetails(EmployeeSalaryDetails empSalaryDetails) {
		EmployeeDetails empDetail = empRepo.findByEmpId(empSalaryDetails.getEmployeeDetails().getEmpId());
		empSalaryDetails.setEmployeeDetails(empDetail);
		EmployeeSalaryDetails saved = empSalRepo.save(empSalaryDetails);

		if (saved != null) {
			ebr.setMsg("Salary Details Added successfully !");
			ebr.setStatus(true);
			ebr.setEmployeeDto(null);
		} else {
			ebr.setMsg("Adding Salary Details Failed");
			ebr.setStatus(false);
			ebr.setEmployeeDto(null);
		}
		return ebr;
	}

	@Override
	public EntityBeanResponse updateSalaryDetails(EmployeeSalaryDetails empSalaryDetails) {
		EmployeeDetails empDetails = empRepo.findByEmpId(empSalaryDetails.getEmployeeDetails().getEmpId());
		empSalaryDetails.setEmployeeDetails(empDetails);
		EmployeeSalaryDetails updated = empSalRepo.save(empSalaryDetails);

		if (updated != null) {
			ebr.setMsg("Salary Details Updated Successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Salary Updation Failed");
			ebr.setStatus(false);
		}
		return ebr;
	}

	/*
	 * @Override public List<EmployeeSalaryDetails> getSalaryByEmpId(String empId) {
	 * 
	 * return empSalRepo.findByEmpId(empId); }
	 */

	@Override
	public EmployeeSalaryDetails getEmpSalaryDetailsById(Integer id) {
		Optional<EmployeeSalaryDetails> findById = empSalRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
	}
		return null;
	}
	@Override
	public EntityBeanResponse saveEmployeeInformation(EmployeeInformation empInformation) {
EmployeeDetails employeeDetails=empRepo.findByEmpId(empInformation.getEmployeeDetails().getEmpId());
		
		empInformation.setEmployeeDetails(employeeDetails);
		

		EmployeeInformation empInfo = empInfRepo.save(empInformation);
		
         if (empInfo != null) {
			ebr.setMsg("Employee Information saved successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Information Saving Failed !");
			ebr.setStatus(false);
		}
         
		return ebr ;
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
         if(findById.isPresent()) {
        	 return findById.get();
         }
		return null;
	}
	
	
	@Override
	public ContactBean saveContactdata(ContactDetails details) {
		
       EmployeeDetails employeedetails=empRepo.findByEmpId(details.getEmployeedetails().getEmpId());
		
       details.setEmployeedetails(employeedetails);
		

       ContactDetails contactdetails =contactrepo.save(details);
		
         if (contactdetails != null) {
        	 contactbean.setMessage("contact details save successfully");
        	 contactbean.setStatus(true);
		} else {
			contactbean.setMessage("contact details not saved");
			contactbean.setStatus(false);
		}


		return contactbean ;


	}


	@Override
	public List<ContactDetails> getContactdata() {
		
		return contactrepo.findAll();
	}

	@Override
	public ContactDetails updateContact(ContactDetails entity) {
		
		EmployeeDetails emp = empRepo.findByEmpId(entity.getEmployeedetails().getEmpId());
		entity.setEmployeedetails(emp);

		return contactrepo.save(entity);
		
	}

}
	
	
	



