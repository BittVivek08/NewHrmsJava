package com.hrms.controller;

import java.text.ParseException;
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
		   
<<<<<<< HEAD
		   EmployeeAttendancebean  attendancebean = new EmployeeAttendancebean();
=======
		   EmployeeAttendancebean attendancebean = new EmployeeAttendancebean();
>>>>>>> 263fa520f10cf84dbe623210aab30fd233f2e7fc
	        boolean isCheckedIn = attendanceService.checkIfCheckedInToday(empId);
	        
	        if (isCheckedIn) {
	        	attendancebean.setMsg("Employee has already checked in today");
	        	attendancebean.setStatus(false);
<<<<<<< HEAD
	        	return ResponseEntity.ok().body(attendancebean);
=======
            return ResponseEntity.ok().body(attendancebean);
            
>>>>>>> 263fa520f10cf84dbe623210aab30fd233f2e7fc
//	        	return new ResponseEntity<EmployeeAttendance>(HttpStatus.BAD_REQUEST);
	        }
	        attendanceService.saveCheckInTime(empId,IPAddress.getCurrentIp(),employeeAttendanceRequest.getWorkFrom());
	        attendancebean.setMsg("Employee checked in successfully");
<<<<<<< HEAD
        	attendancebean.setStatus(true);
        	return ResponseEntity.ok().body(attendancebean);
=======
	        attendancebean.setStatus(true);
	        return ResponseEntity.ok(attendancebean);
				
>>>>>>> 263fa520f10cf84dbe623210aab30fd233f2e7fc
	    }
	   
	   @PostMapping("/check-out/{empId}")
	    public ResponseEntity<EmployeeAttendancebean> checkOut(@PathVariable String empId) {
		   
		   EmployeeAttendancebean attendancebean = new EmployeeAttendancebean();
<<<<<<< HEAD
	        attendanceService.saveCheckOutTime(empId);
	        attendancebean.setMsg("Employee checked out successfully");
        	attendancebean.setStatus(true);
=======
		   attendanceService.saveCheckOutTime(empId);
		   attendancebean.setMsg("Employee checked out successfully");
		   attendancebean.setStatus(true);
>>>>>>> 263fa520f10cf84dbe623210aab30fd233f2e7fc
	        return ResponseEntity.ok(attendancebean);
	    }
	   
	   @GetMapping("/employee/weekly/{empId}")
	    public ResponseEntity<List<EmployeeAttendance>> getEmployeeWeeklyAttendance(@PathVariable String empId,
	    		@QueryParam("startDate") String startDate,
	    		@QueryParam("endDate") String endDate) throws ParseException {
		   
//		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        List<EmployeeAttendance> attendanceList = attendanceService.getEmployeeWeeklyAttendance(empId, startDate, endDate);

	        return ResponseEntity.ok(attendanceList);
	    }
	   
	   
//	   @PostMapping("/saveEmployee")
//		public  EmployeeAttendancebean recordAttendance(@RequestBody EmployeeAttendance employeeattend){
//			
//			return attendanceService.saveAttendanceDetails(employeeattend);
//
//		}
	   
	   
}