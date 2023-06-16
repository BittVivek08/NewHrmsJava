package com.hrms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.beans.CommonResponseBean;
import com.hrms.beans.ContactBean;
import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EmployeeEducationDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.ContactDetails;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeInformation;
import com.hrms.entity.EmployeeSalaryDetails;
import com.hrms.repository.EmployeeRepository;
import com.hrms.request.bean.EmployeeSalaryRequestBean;
import com.hrms.service.EmployeeDetailsService;
import com.hrms.service.FileStorageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Employee")
public class EmployeeDetailsController {

	@Autowired
	private EmployeeDetailsService empService;

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	private EmployeeRepository empRepo;

	@PostMapping("/saveEmployee")
	public EntityBeanResponse saveEmpDetails(@RequestBody EmployeeDetails empDetails) {
		return empService.saveEmpDetails(empDetails);
	}

	@PostMapping("/uploadImage") public EntityBeanResponse saveEmpDetails(@RequestParam String jsonData,@RequestParam("file") MultipartFile file) throws IOException {
		try{
			log.info("Class : "+this.getClass().getName());
			log.info("Method :" + Thread.currentThread().getStackTrace()[1].getMethodName());
			String fileName = fileStorageService.storeFile(file);
			EmployeeDetails empDetails = new ObjectMapper().readValue(jsonData, EmployeeDetails.class);
			new ObjectMapper().writeValueAsString(jsonData);
			empDetails.setProfileImg(fileName); 
			EntityBeanResponse response = empService.saveEmpDetails(empDetails);
			return response;
		}
		catch(Exception e) {
			return null;
		}
	}

	@GetMapping("/getEmployeeDetails")
	public ResponseEntity<List<EmployeeDetails>> getAllEmployeeDetails() {
		List<EmployeeDetails> allEmpDetails = empService.getAllEmpDetails();
		return new ResponseEntity<>(allEmpDetails, HttpStatus.OK);
	}

	@GetMapping("/getEmployeeDetail/{id}")
	public ResponseEntity<EmployeeDetails> getEmployeeById(@PathVariable Integer id) {
		EmployeeDetails empById = empService.getEmpById(id);
		return new ResponseEntity<>(empById, HttpStatus.OK);
	}
	
	@GetMapping("/getEmployeeByempId/{empId}")
	public ResponseEntity<EmployeeDetails> getEmployeeByEmpId(@PathVariable String empId) {
		EmployeeDetails empById = empService.getEmpByEmpId(empId);
		return new ResponseEntity<>(empById, HttpStatus.OK);
	}

	@PutMapping("/updateEmployeeDetails/{empId}")
	public EntityBeanResponse updateEmplyee(@RequestBody EmployeeDetails empDetails,@PathVariable("empId") String empId) {
		return empService.updateEmpDetails(empDetails,empId);
	}

	@GetMapping("/birthdays")
	public List<EmpBirthResponse> getMatchingEmployeeBirthdays() {
		List<EmpBirthResponse> an = empService.findBirthdayDetails();

		return an;
	}

	/*
	 * @PostMapping("/login") public EntityBeanResponse loginEmployee(@RequestBody
	 * LoginDto loginDto){ EntityBeanResponse loginEmployee =
	 * empService.loginEmployee(loginDto); return loginEmployee; }
	 */

	@PostMapping("/login")
	public ResponseEntity<String> loginEmployee(@RequestBody LoginDto loginDto) throws JsonProcessingException {
		EntityBeanResponse loginEmployee = empService.loginEmployee(loginDto);
		// String json = new ObjectMapper().writeValueAsString(loginEmployee);

		String formattedJsonString = new ObjectMapper().writerWithDefaultPrettyPrinter()
				.writeValueAsString(loginEmployee);

		if (loginEmployee.getEmployeeDto() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(formattedJsonString);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

		/*
		 * @PostMapping("/changepassword") public PasswordChangeResponse
		 * changePassword(@RequestParam ("oldPassword") String oldPassword,
		 * 
		 * @RequestParam ("newPassword") String newPassword, LoginDto loginDto){
		 * 
		 * PasswordChangeResponse pcr =new PasswordChangeResponse();
		 * 
		 * String email = loginDto.getEmail(); EmployeeDetails currentEmployee =
		 * empRepo.findByEmail(email);
		 * 
		 * if(this.bCryptPasswordEncoder.matches(oldPassword,
		 * currentEmployee.getPassword())) {
		 * currentEmployee.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		 * this.empRepo.save(currentEmployee);
		 * 
		 * 
		 * pcr.setMsg("Password Change Successfully"); pcr.setStatus(true); }else {
		 * pcr.setMsg("Password Change process Failed"); pcr.setStatus(true); } return
		 * pcr; }
		 */
	}

	@PostMapping("/saveEmpInfo/{empId}") 
	public EntityBeanResponse saveEmployeeInformation(@PathVariable String empId,@RequestBody EmployeeInformation empInformation) {

		return empService.saveEmployeeInformation(empId,empInformation);
	}   

	@PutMapping("/UpdateEmpInfo/{empId}")
	public EntityBeanResponse updateEmpInfo(@RequestBody EmployeeInformation empInfo,@PathVariable("empId") String empId) {
		EmployeeDetails byEmp = empRepo.findByEmpId(empId);
		empInfo.setEmployeeDetails(byEmp);
		return empService.updateEmployeeInformation(empInfo);
	}


	@GetMapping("/getEmpInfo/{id}")
	public ResponseEntity<EmployeeInformation> findEmpInfoById(@PathVariable Integer id){
		EmployeeInformation empInfoById = empService.getEmpInfoById(id);
		return new ResponseEntity<>(empInfoById,HttpStatus.OK);
	}
	
	@GetMapping("/getEmpInfoempId/{empId}")
	public ResponseEntity<EmployeeInformation> findEmpInfoByEmpId(@PathVariable String empId){
		EmployeeInformation empInfoById = empService.getEmpInfoByEmpId(empId);
		return new ResponseEntity<>(empInfoById,HttpStatus.OK);
	}

	@PostMapping("/saveContactDetails/{empId}") 
	public ContactBean saveContactDetails(@PathVariable String empId,  @RequestBody ContactDetails details) {
        log.info("entered saveContactDetails method of contoller class");
		return empService.saveContactdata(empId,details);
	} 

	@GetMapping("/getAllContactDetails")
	public ResponseEntity<List<ContactDetails>> getAllontactDetails() {
		log.info("entered into getAllcontactDetails method for fetching data in controller class");
		List<ContactDetails> datails = empService.getContactdata();
		if (datails.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(datails);
	}

	@PutMapping("/updateContactDetails/{empId}")
	public ResponseEntity<ContactBean> updateContactDetails(@RequestBody ContactDetails entity,@PathVariable("empId") String empId) {
		log.info("entered into updatecontactdetails method in controller class");
		ContactBean updatedetails = empService.updateContact(entity,empId);
		return ResponseEntity.ok(updatedetails);
	}
	@GetMapping("/getContactDetails/{id}")
	public ContactDetails getContactDataById(@PathVariable int id) {
        log.info("entered into getcontactdatabyid method in controller class");
		ContactDetails details=empService.getcontactDetails(id);
		return details;
	}
	@GetMapping("/getContactempId/{empId}")
	public ContactDetails getContactDataByEmpId(@PathVariable String empId) {
       log.info("entered into getcontactDataByEmpId method in controller class ");
		ContactDetails details=empService.getcontactDetails(empId);
		return details;
	}

	@PostMapping("/saveEmpeducationdetails/{empId}")
	public EmployeeEducationDetailsBean saveEmployeeeducationdetails(@RequestBody EmployeeEducationDetails empeducationdetails,@PathVariable String empId) {
		return empService.saveEmployeeeducationdetails(empeducationdetails,empId);
	}   

	@PutMapping("/UpdateEmpeducationdetails/{empId}")
	public EmployeeEducationDetailsBean updateEmployeeeducationdetails(@RequestBody EmployeeEducationDetails empeducationdetails,@PathVariable("empId") String empId) {
		
		return empService.updateEmployeeeducationdetails( empeducationdetails,empId);
	}

	@GetMapping("/getEmpeducationdetails/{id}")
	public ResponseEntity<EmployeeEducationDetails> findEmpeducationdetailsById(@PathVariable Integer id){
		EmployeeEducationDetails empeducationById = empService.getEmpeducationdetalsById(id);
		return new ResponseEntity<>(empeducationById,HttpStatus.OK);
	}
	
	@GetMapping("/getEmpeducationempId/{empId}")
	public ResponseEntity<EmployeeEducationDetails> findEmpeducationdetailsByEmpId(@PathVariable String empId){
		EmployeeEducationDetails empeducationById = empService.getEmpeducationdetalsByEmpId(empId);
		return new ResponseEntity<>(empeducationById,HttpStatus.OK);
	}

	@GetMapping("/getEmpeducationdetails")
	public ResponseEntity<List<EmployeeEducationDetails>> getAllEmployeeEducationdetails() {
		List<EmployeeEducationDetails> allEmpeducationDetails = empService.getAllEmpeducationdetails();
		return new ResponseEntity<>(allEmpeducationDetails, HttpStatus.OK);
	}
	
	@PostMapping("/saveEmpSalary/{empId}")
	public CommonResponseBean saveEmpSalDetails(@PathVariable String empId,@RequestBody EmployeeSalaryRequestBean empSalReqBean) {
		return empService.saveSalaryDetails(empId, empSalReqBean);
	}
	
	@PutMapping("/updateEmpSalary/{empId}")
	public CommonResponseBean updateEmpSalDetails(@RequestBody EmployeeSalaryDetails empsalDetails,@PathVariable("empId") String empId) {
		return empService.updateSalaryDetails(empsalDetails,empId);
	}
	
	@GetMapping("/salById/{id}")
	public ResponseEntity<EmployeeSalaryDetails> getEmpSalById(@PathVariable Integer id){
		EmployeeSalaryDetails empSalaryById = empService.getEmpSalaryById(id);
		return new ResponseEntity<>(empSalaryById,HttpStatus.OK);
	}
	
	@GetMapping("/salByEmpId/{empId}")
	public ResponseEntity<EmployeeSalaryDetails> getEmpSalById(@PathVariable String empId){
		EmployeeSalaryDetails empSalaryById = empService.getEmpSalaryByEmpId(empId);
		return new ResponseEntity<>(empSalaryById,HttpStatus.OK);
	}

}
