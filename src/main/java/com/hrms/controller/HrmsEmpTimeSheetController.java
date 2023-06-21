package com.hrms.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.ProjectListResponse;
import com.hrms.response.bean.TimeSheetRequestRepDate;
import com.hrms.response.bean.TimeSheetResponse;
import com.hrms.serviceImpl.TimeSheetDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/TimeSheet")
public class HrmsEmpTimeSheetController {
	@Autowired
	private TimeSheetDetailsImpl impl;

	@PostMapping("/timeSheetDetails")
	public ResponseEntity<TimeSheetResponse> saveTimeSheett(@RequestBody List<SaveTimeSheet> timesheet) {
		log.info("entered into saveTimeSheet method of HrmsEmpTimeSheetController class");
		try {
			TimeSheetResponse saveTimeSheett1 = this.impl.saveTimeSheett(timesheet);
			return new ResponseEntity<>(saveTimeSheett1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/timeSheetDetails")
	public List<SaveTimeSheet> getTimeSheetDetails(@QueryParam("empId") String empId) {
		log.info("entered into getTimeSheetDetails method of HrmsEmpTimeSheetController class");

		List<SaveTimeSheet> saveTimeSheett1 = this.impl.getTimeSheett(empId);

		return saveTimeSheett1;

	}

	@PutMapping("/timeSheetDetails")
	public ResponseEntity<TimeSheetResponse> updateTimeSheetDetails(@RequestBody SaveTimeSheet timesheet,
			@QueryParam("id") int id) {
		log.info("entered into updateTimeSheetDetails method of HrmsEmpTimeSheetController class");
		try {
			TimeSheetResponse saveTimeSheett1 = this.impl.UpdateTimeSheett(timesheet, id);
			return new ResponseEntity<>(saveTimeSheett1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/timeSheetDetailsByDate")
	public List<SaveTimeSheet> getTimeSheetDetailsByDate(@RequestBody TimeSheetRequestRepDate date) {
		log.info("entered into getTimeSheetDetailsByDate method of HrmsEmpTimeSheetController class");
		List<SaveTimeSheet> saveTimeSheett1 = this.impl.getTimeSheetByDate(date);
		return saveTimeSheett1;
	}

	@PostMapping("/timesheetDetailsStartDateEndDate")
	public List<SaveTimeSheet> getTimeSheetDetailsByStartDateEndDate(@RequestBody TimeSheetRequestRepDate date) {
		log.info("Entered into getTimeSheetDetailsByStartDateEndDate method of HrmsEmpTimeSheetController class");
		List<SaveTimeSheet> savetimesheet = this.impl.getTimeSheetByStartDateEndDate(date);
		return savetimesheet;

	}

	@GetMapping("timesheetDetailsRpid")
	public List<EmployeeDetails> getTimeSheetDetailsUsingRid(@QueryParam("repId") String repId) {
		log.info("Entered into getTimeSheetDetailsUsingRpid method of controller class");
		List<EmployeeDetails> savetimesheet = this.impl.getTimeSheetByRpId(repId);
		return savetimesheet;
	}

	@GetMapping("/timesheetDetailsProjectList")
	public ProjectListResponse getProjectIdList() {
		log.info("Entered into getProjectIdList method of HrmsEmpTimeSheetController class");
		return impl.getProjectList();
	}

	@GetMapping("timesheetDetailsRepIdEmpId")
	public List<SaveTimeSheet> getTimeSheetDetailsUsingRepidEmpId(@QueryParam("repId") String repId,
			@QueryParam("empId") String empId) {
		log.info("Entered into getTimeSheetDetailsUsingRepidEmpId method of controller class");
		List<SaveTimeSheet> savetimesheet = this.impl.getTimeSheetByRpIdEmpId(repId, empId);
		return savetimesheet;
	}

	@PostMapping("timesheetDetailsRepIdDate")
	public List<SaveTimeSheet> getTimeSheetDetailsUsingRepidDate(@RequestBody TimeSheetRequestRepDate timesheet) {
		log.info("Entered into timesheetDetailsRepIdDate method of controller class");
		List<SaveTimeSheet> savetimesheet = this.impl.getTimeSheetByRpIdDate(timesheet);
		return savetimesheet;
	}

	@PostMapping("timeSheetDetailsRepIdStartEndDateEmpId")
	public List<SaveTimeSheet> getTimeSheetDetailsUsingMan(@RequestBody TimeSheetRequestRepDate timesheet) {
		log.info("Entered into getTimeSheetDetailsUsingRepidEmpId method of controller class");
		List<SaveTimeSheet> savetimesheet = this.impl.getTimeSheetByMan(timesheet);
		return savetimesheet;
	}

}