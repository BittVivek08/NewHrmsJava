package com.hrms.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.beans.EmployeeAttendanceRequest;
import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.entity.HolidayCalenderEntity;
import com.hrms.entity.RequestForLeave;
import com.hrms.service.EmployeeAttendanceService;
import com.hrms.util.IPAddress;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeAttendanceController {

	@Autowired
	private EmployeeAttendanceService attendanceService;

	@PostMapping("/check-in/{empId}")
	public ResponseEntity<EmployeeAttendancebean> checkIn(@PathVariable String empId,
			@RequestBody EmployeeAttendanceRequest employeeAttendanceRequest) {
		log.info("attendance checkin api");
		EmployeeAttendancebean attendancebean = attendanceService.saveCheckInTime(empId, IPAddress.getCurrentIp(),
				employeeAttendanceRequest.getWorkFrom());
		return ResponseEntity.ok(attendancebean);
	}

	@PostMapping("/check-out/{empId}")
	public ResponseEntity<EmployeeAttendancebean> checkOut(@PathVariable String empId) {
		
		log.info("attendance checkout api");
		EmployeeAttendancebean attendancebean = new EmployeeAttendancebean();
		attendanceService.saveCheckOutTime(empId);
		attendancebean.setMsg("Employee checked out successfully");
		attendancebean.setStatus(true);
		return ResponseEntity.ok(attendancebean);
	}

	@GetMapping("/employee/weekly/{empId}")
	public ResponseEntity<List<Object>> getEmployeeWeeklyAttendance(@PathVariable String empId,
			@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws ParseException {
		log.info("get attendance details api");
//		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeAttendance> attendanceList = attendanceService.getEmployeeWeeklyAttendance(empId, startDate,endDate);
		List<HolidayCalenderEntity> holidaylist = attendanceService.findHolidaysByDateRange(startDate, endDate);
		List<String> weekendList = attendanceService.getWeekendsBetweenDates(startDate, endDate);
		List<RequestForLeave> leaveList = attendanceService.getLeaveRecords(startDate, endDate);

		// Combine all results into a single list
		List<Object> result = new ArrayList<>();
		result.addAll(attendanceList);
		result.addAll(holidaylist);
	    result.addAll(leaveList);
		result.addAll(weekendList);

		return ResponseEntity.ok(result);

	}
	
	@PostMapping("/check-in-forcely/{empId}")
	public ResponseEntity<EmployeeAttendancebean> checkInForcely(@PathVariable String empId,
			@RequestBody EmployeeAttendanceRequest employeeAttendanceRequest) {
		log.info("attendance checkin forcely api");
		EmployeeAttendancebean attendancebean = attendanceService.saveCheckInTimeForcely(empId, IPAddress.getCurrentIp(),
				employeeAttendanceRequest.getWorkFrom());
		return ResponseEntity.ok(attendancebean);
	}

	// @PostMapping("/saveEmployee")
	// public EmployeeAttendancebean recordAttendance(@RequestBody
	// EmployeeAttendance employeeattend){
	//
	// return attendanceService.saveAttendanceDetails(employeeattend);
	//
	// }

}