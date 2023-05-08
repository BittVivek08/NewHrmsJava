package com.hrms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.QueryParam;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EntityBeanResponse;
//import com.hrms.beans.SaveTimesheetRequestBean;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.serviceImpl.TimeSheetDetailsImpl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin
public class HrmsEmpTimeSheetController {
     @Autowired
	private TimeSheetDetailsImpl impl;

     
//  @PostMapping("/saveTimeSheet")
//public ResponseEntity<String> saveTimeSheet(@RequestBody SaveTimesheetRequestBean saveReq) {
//	log.info("entered into saveTimeSheet method of HrmsEmpTimeSheetService class");
//	try {
//	  impl.saveTimeSheet(saveReq);
//	  return  new ResponseEntity<>("Successfully save data in data base",HttpStatus.OK);
//	}catch(Exception e) {
//	 return  new ResponseEntity<>("not found data",HttpStatus.OK);
//	}
//}
//  
//	@GetMapping("/getMonthlyTSDetailsOfEmp/{userid}/{monthEndDate}")
//	public ResponseEntity<String> getMonthlyTSDetailsOfEmp(@PathVariable("userid") int userid,
//			@PathVariable("monthStartDate") String monthStartDate, @PathVariable("monthEndDate") String monthEndDate) {
//		log.info("entered into getMonthlyTSDetailsOfEmp method of HrmsEmpTimeSheetService class");
//		 impl.getMonthlyDataInTimeSheet(userid, monthStartDate, monthEndDate);
//		 return  new ResponseEntity<>("successfully Data retrived",HttpStatus.OK);
//	}
//	
//	@GetMapping("/Time/{id}")
//    public ResponseEntity<SavingTimeSheet> get(@PathVariable("id") Integer id) {
//        try {
//        	SavingTimeSheet  timeSheet= impl.getTimeSheet(id);
//            return new ResponseEntity<SavingTimeSheet>(timeSheet, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<SavingTimeSheet>(HttpStatus.NOT_FOUND);
//        }
//    }
//	 @DeleteMapping("/Time/{id}")
//     public ResponseEntity<String> deleteTimeSheet(@PathVariable("id")int id) {
//    	try { 
//     	this.impl.deleteUser(id);
//     	 return new ResponseEntity<>("SuccessFullyDeleted TimeSheet", HttpStatus.ACCEPTED);
//    	}catch(Exception e) {
//    		return  new ResponseEntity<>("user not present in data base",HttpStatus.NOT_FOUND);
//    	}
//    } 
//	 
	 @PostMapping("/saveTimeSheett")
	 public ResponseEntity<String> saveTimeSheett(@RequestBody SaveTimeSheet hrms ) {
	 	log.info("entered into saveTimeSheet method of HrmsEmpTimeSheetService class");
	 	try {
	 		impl.saveTimeSheett(hrms);
	 	  return  new ResponseEntity<>("successfully added data",HttpStatus.OK);
	 	}catch(Exception e) {
	 	 return  new ResponseEntity<>("data not added successfully",HttpStatus.OK);
	 	}
	 } 
	 @DeleteMapping("/DeleteTimeSheet")
     public ResponseEntity<String> deleteTimeSheetById(@QueryParam("id")int id) {
    	try { 
     	this.impl.deleteTimeSheet(id);
     	 return new ResponseEntity<>("Successfully Deleted TimeSheet", HttpStatus.ACCEPTED);
    	}catch(Exception e) {
    		return  new ResponseEntity<>("user not present in data base",HttpStatus.NOT_FOUND);
    	}
    } 
	    @GetMapping("/getTimeSheetbyId")
	    public ResponseEntity<SaveTimeSheet> getDetail(@QueryParam("id") int id) {
	        try {
	        	SaveTimeSheet  timeSheet= impl.getTimeSheetDetail(id);
	            return new ResponseEntity<SaveTimeSheet>(timeSheet, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<SaveTimeSheet>(HttpStatus.NOT_FOUND);
	        }
	    }
	    @PutMapping("/UpdateTimeSheetData")
        public ResponseEntity<String> updateTimeSheet(@RequestBody SaveTimeSheet hrms ,@QueryParam("id") int id) {

	    	try {
        this.impl.updateTimeSheet(hrms,id);
        return new ResponseEntity<>("Successfully Updated data this id: "+id, HttpStatus.OK);
	    	}catch(Exception e) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	}
	    @GetMapping("/getDetailUsingMonYearWeek")
	    public ResponseEntity<SaveTimeSheet> getEmployeeDetailByMonYearandWeek(@QueryParam("month")int month ,@QueryParam("year")int year,@QueryParam("calweek") int calweek,@QueryParam("id") int id) {
	        
	       SaveTimeSheet timesheet=impl.getTimeSheetDetails(month,year,calweek,id);
	       if(timesheet!=null) {
//	            return ResponseEntity.ok(timesheet);
	            return new ResponseEntity<SaveTimeSheet>(timesheet,HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    
	    @GetMapping("/getTimeSheetDetailUsingMon")
	    public ResponseEntity<List<Object[]>> getEmployeeDetailByMonYearandWeek(@QueryParam("month")int month,@QueryParam("empId") int empId ,@QueryParam("year") int year) {
	        try {
	       List<Object[]> timesheet=impl.getTimeSheetDetailsByMonth(month,empId,year);
	        return new ResponseEntity<List<Object[]>>(timesheet,HttpStatus.OK);
	        } catch (NoSuchElementException e) {
//	        	ResponseEntity.status(HttpStatus.ACCEPTED).body("not found data");
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
//	   @GetMapping("/getEmployeesByReportingManagerId")
//	   public ResponseEntity<List<SaveTimeSheet>> getEmpByRepID(@QueryParam("repId") int repId){
//		   List<SaveTimeSheet> ob=impl.getEmpByReportingId(repId);
//		   return new ResponseEntity<List<SaveTimeSheet>>(ob,HttpStatus.OK);
//		   
//	   }
	    
		/*
		 * @PostMapping("/timeSheetApproval") public
		 * ResponseEntity<TimeSheetApprovalStatus>
		 * timeSheetApproval(@QueryParam("empId") int empid){ TimeSheetApprovalStatus
		 * timesheet=impl.timeSheetApproval(empid); if(timesheet!=null) { return new
		 * ResponseEntity<TimeSheetApprovalStatus>(timesheet,HttpStatus.ACCEPTED) ;
		 * }else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
		 */
	    
     }

