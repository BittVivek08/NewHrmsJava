package com.hrms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeInformation;
import com.hrms.entity.EmployeeSalaryDetails;
import com.hrms.repository.EmployeeRepository;
import com.hrms.service.EmployeeDetailsService;
import com.hrms.service.FileStorageService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin
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
			empDetails.setImage(fileName); 
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

	@PutMapping("/updateEmployeeDetails")
	public EntityBeanResponse updateEmplyee(@RequestBody EmployeeDetails empDetails) {
		return empService.updateEmpDetails(empDetails);
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

	@PostMapping("/saveSalaryDetails")
	public EntityBeanResponse saveEmpSalaryDetails(@RequestBody EmployeeSalaryDetails empSalDetails) {
		EmployeeDetails byEmpId = empRepo.findByEmpId(empSalDetails.getEmployeeDetails().getEmpId());
		empSalDetails.setEmployeeDetails(byEmpId);
		return empService.saveSalaryDetails(empSalDetails);
	}
	@PutMapping("/updateSalaryDetails")
	public EntityBeanResponse updateEmpSalaryDetails(@RequestBody EmployeeSalaryDetails empSalDetails) {
		EmployeeDetails byEmp = empRepo.findByEmpId(empSalDetails.getEmployeeDetails().getEmpId());
		empSalDetails.setEmployeeDetails(byEmp);
		return empService.updateSalaryDetails(empSalDetails);
	}


	/*
	 * @GetMapping("/getSalaryByEmpId/{empId}") public List<EmployeeSalaryDetails>
	 * getSalaryByEmpId(@PathVariable String empId){ return
	 * empService.getSalaryByEmpId(empId); }
	 */
	@GetMapping("/getEmployeeSalaryDetail/{id}")
	public ResponseEntity<EmployeeSalaryDetails> findSalaryById(@PathVariable Integer id){
		EmployeeSalaryDetails empSalaryDetailsById = empService.getEmpSalaryDetailsById(id);
		return new ResponseEntity<>(empSalaryDetailsById,HttpStatus.OK);
	}

	@PostMapping("/saveEmpInfo") 
	public EntityBeanResponse saveEmployeeInformation(@RequestBody EmployeeInformation empInformation) {

		return empService.saveEmployeeInformation(empInformation);
	}   

	@PutMapping("/UpdateEmpInfo")
	public EntityBeanResponse updateEmpInfo(@RequestBody EmployeeInformation empInfo) {
		EmployeeDetails byEmp = empRepo.findByEmpId(empInfo.getEmployeeDetails().getEmpId());
		empInfo.setEmployeeDetails(byEmp);
		return empService.updateEmployeeInformation(empInfo);
	}


	@GetMapping("/getEmpInfo/{id}")
	public ResponseEntity<EmployeeInformation> findEmpInfoById(@PathVariable Integer id){
		EmployeeInformation empInfoById = empService.getEmpInfoById(id);
		return new ResponseEntity<>(empInfoById,HttpStatus.OK);
	}


}
