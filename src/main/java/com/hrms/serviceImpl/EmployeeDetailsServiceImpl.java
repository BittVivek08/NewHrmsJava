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
import com.hrms.beans.EmployeeEducationDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.ContactDetails;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeInformation;
import com.hrms.repository.ContactRepo;
import com.hrms.repository.EmployeeEducationDetailsRepository;
import com.hrms.repository.EmployeeInformationRepository;
import com.hrms.repository.EmployeeRepository;

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
	private PasswordEncoder passwordEncoder;

	private ContactRepo contactrepo;

	@Autowired
	private ContactBean contactbean;

	@Autowired
	public EmployeeEducationDetailsBean empeducationbean;


	@Autowired
	public EmployeeEducationDetailsRepository empeducationrepo;


	@Override
	public EntityBeanResponse saveEmpDetails(EmployeeDetails employeeDetails) {
		String encode = this.passwordEncoder.encode(employeeDetails.getPassword());
		employeeDetails.setPassword(encode);

		EmployeeDetails saved = empRepo.save(employeeDetails);
		EmployeeDetails findByEmail = empRepo.findByEmail(employeeDetails.getEmail());


		if (saved != null) {
			EmployeeDto empDto = new EmployeeDto();

			empDto.setId(findByEmail.getId());
			empDto.setEmpId(findByEmail.getEmpId());
			empDto.setFirstName(findByEmail.getFirstName());
			empDto.setLastName(findByEmail.getLastName());
			empDto.setEmpRole(findByEmail.getEmpRole());
			empDto.setEmail(findByEmail.getEmail());
			empDto.setGender(findByEmail.getGender());
			empDto.setDateOfBirth(findByEmail.getDateOfBirth());
			empDto.setQualification(findByEmail.getQualification());
			empDto.setMobileNumber(findByEmail.getMobileNumber());
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
	public EntityBeanResponse updateEmpDetails(EmployeeDetails emplDetails) {
		Optional<EmployeeDetails> employeeDetails = empRepo.findById(emplDetails.getId());
		emplDetails.setPassword(employeeDetails.get().getPassword());

		EmployeeDetails update = empRepo.save(emplDetails);
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

	public EntityBeanResponse saveEmployeeInformation(String empId,EmployeeInformation empInformation) {


		EmployeeDetails employeeDetails=empRepo.findByEmpId(empId);
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
	public ContactDetails getcontactDetails(int id) {

		Optional<ContactDetails> contact= contactrepo.findById(id);
		if(contact.isPresent()) {
			return contact.get();

		}
		return null;
	}


	@Override
	public EmployeeEducationDetailsBean saveEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails,String empId) {

		//	EmployeeDetails employeeDetails=empRepo.findByEmpId(empeducationdetails.getEmployeeDetails().getEmpId());

		EmployeeDetails employeeDetails=empRepo.findByEmpId(empId);

		empeducationdetails.setEmployeeDetails(employeeDetails);


		EmployeeEducationDetails empeducation = empeducationrepo.save(empeducationdetails);

		if (empeducation != null) {
			empeducationbean.setMessage("EmployeeEducation details saved successfully");
			empeducationbean.setStatus(true);
		} else {
			empeducationbean.setMessage("EmployeeEducation details Saving Failed !");
			empeducationbean.setStatus(false);
		}

		return empeducationbean ;


	}

	@Override
	public EmployeeEducationDetailsBean updateEmployeeeducationdetails(EmployeeEducationDetails empeducationdetails) {

		EmployeeDetails employeeDetails=empRepo.findByEmpId(empeducationdetails.getEmployeeDetails().getEmpId());

		empeducationdetails.setEmployeeDetails(employeeDetails);


		EmployeeEducationDetails empeducation = empeducationrepo.save(empeducationdetails);

		if (empeducation != null) {
			empeducationbean.setMessage("EmployeeEducation details updated successfully");
			empeducationbean.setStatus(true);
		} else {
			empeducationbean.setMessage("EmployeeEducation details Failed !");
			empeducationbean.setStatus(false);
		}

		return empeducationbean ;
	}

	@Override
	public EmployeeEducationDetails getEmpeducationdetalsById(Integer id) {

		Optional<EmployeeEducationDetails> empeducation = empeducationrepo.findById(id);
		if(empeducation.isPresent()) {
			return empeducation.get();
		}
		return null;
	}

	@Override
	public List<EmployeeEducationDetails> getAllEmpeducationdetails() {

		return empeducationrepo.findAll();
	}
}