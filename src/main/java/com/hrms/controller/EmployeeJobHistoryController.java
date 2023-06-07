package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.JobHistoryResponse;
import com.hrms.entity.EmployeeJobHistory;
import com.hrms.service.EmployeeJobHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeJobHistoryController {

	@Autowired
	private EmployeeJobHistoryService employeeJobHistoryService;

	// for saving job history details.
	@PostMapping("/saveJobHistory")
	public JobHistoryResponse saveJobHistory(@RequestBody EmployeeJobHistory employeeJobHistory) {

		log.info("employee save job history api");
		return employeeJobHistoryService.saveJobHistory(employeeJobHistory);


	}

	// for display the particular details by usin id
	@GetMapping("/getDetails/{positionId}")
	public EmployeeJobHistory  getByPositionId(@PathVariable("positionId") int positionId)
	{
		log.info("employee job history get by position id api");
		return employeeJobHistoryService.getByPositionId(positionId);	
	}

	//for display the all employee history details
	@GetMapping("/getAllEmployeeHistory")
	public ResponseEntity<List<EmployeeJobHistory>> getAllEmployeeHistory()
	{
		log.info("get all employee job history api");
		List<EmployeeJobHistory> allejh =employeeJobHistoryService.getAllJobHistory();
		return new ResponseEntity<>(allejh,HttpStatus.OK);
	}

	// for delete the particular details by using id
	@DeleteMapping("delete/{positionId}")
	public JobHistoryResponse deleteByPositionId(@PathVariable("positionId") int positionId)
	{
		log.info("delete employee job history by position id api");
		return employeeJobHistoryService.deletePositionId(positionId);	
	}

	// for update the particular details by using id
	@PutMapping("/update/{positionId}")
	public ResponseEntity<EmployeeJobHistory> updateDetailsById(@PathVariable("positionId") int positionId,@RequestBody EmployeeJobHistory employeeJobHistory)
	{
		log.info("update employee job history by position id api");
		EmployeeJobHistory empJobHistory=employeeJobHistoryService.updateJobHistory(positionId, employeeJobHistory);
		return  ResponseEntity.ok(empJobHistory);
	}
}
