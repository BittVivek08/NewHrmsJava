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
import com.hrms.service.EmployeeAttendanceService;
import com.hrms.util.IPAddress;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class EmployeeAttendanceController {
	
	   @Autowired
	   private EmployeeAttendanceService attendanceService;
	   
//	   @Autowired
//	   private AttendanceRepository attendanceRepo;
//	   
	   @PostMapping("/check-in/{empId}" )
	    public ResponseEntity<EmployeeAttendancebean> checkIn(@PathVariable String empId, @RequestBody EmployeeAttendanceRequest employeeAttendanceRequest) {
		   
		   EmployeeAttendancebean attendancebean=  attendanceService.saveCheckInTime(empId,IPAddress.getCurrentIp(),employeeAttendanceRequest.getWorkFrom());
	        return ResponseEntity.ok(attendancebean);
	    }
	   
	   @PostMapping("/check-out/{empId}")
	    public ResponseEntity<EmployeeAttendancebean> checkOut(@PathVariable String empId) {
		   
		   EmployeeAttendancebean attendancebean = new EmployeeAttendancebean();
	        attendanceService.saveCheckOutTime(empId);
	        attendancebean.setMsg("Employee checked out successfully");
        	attendancebean.setStatus(true);
	        return ResponseEntity.ok(attendancebean);
	    }
	   
	   @GetMapping("/employee/weekly/{empId}")
	    public ResponseEntity<List<Object>> getEmployeeWeeklyAttendance(@PathVariable String empId,
	    		@QueryParam("startDate") String startDate,
	    		@QueryParam("endDate") String endDate) throws ParseException {
		   
//		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        List<EmployeeAttendance> attendanceList = attendanceService.getEmployeeWeeklyAttendance(empId, startDate, endDate);
	        
	        List<HolidayCalenderEntity> holidaylist =attendanceService.findHolidaysByDateRange(startDate, endDate);
	        
	        List<String> weekendList = attendanceService.getWeekendsBetweenDates(startDate, endDate);
	        // Combine all results into a single list
	        List<Object> result = new ArrayList<>();
	        result.addAll(attendanceList);
	        result.addAll(holidaylist);
//	        result.addAll(leaveList);
	        result.addAll(weekendList);
	        
	        return ResponseEntity.ok(result);
	        
	}
	   
	   
	   
	   
	   

	//	   @PostMapping("/saveEmployee")
	//		public  EmployeeAttendancebean recordAttendance(@RequestBody EmployeeAttendance employeeattend){
	//			
	//			return attendanceService.saveAttendanceDetails(employeeattend);
	//
	//		}


}