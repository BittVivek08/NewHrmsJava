package com.hrms.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.CurrentWeekDatesResponse;
import com.hrms.beans.CurrentWeekRequest;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.CommonTimeSheetbean;
import com.hrms.response.bean.DateResponseTimeSheet;
import com.hrms.response.bean.ProjectListResponse;
import com.hrms.response.bean.TSResponseObj;
import com.hrms.response.bean.TimeSheetApprovalStatusResponse;
import com.hrms.response.bean.TimeSheetResponse;
import com.hrms.response.bean.TimeSheetResponseForMonth;
import com.hrms.response.bean.TimeSheetResponseForMonthYearWeek;
import com.hrms.serviceImpl.TimeSheetDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/TimeSheet")
public class HrmsEmpTimeSheetController {
	@Autowired
	private TimeSheetDetailsImpl impl;

	@PostMapping("/saveTimeSheet")
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

	@DeleteMapping("/DeleteWeekDetailsById")
	public ResponseEntity<String> deleteTimeSheetById(@QueryParam("id") int id) {

		log.info("entered into DeleteTimeSheet method of HrmsEmpTimeSheetController class");
		try {
			this.impl.deleteTimeSheet(id);
			return new ResponseEntity<>("Successfully Deleted TimeSheet", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("user not present in data base", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getTimeSheetbyId")
	public ResponseEntity<SaveTimeSheet> getDetail(@QueryParam("id") int id) {
		log.info("entered into getTimeSheetbyId method of HrmsEmpTimeSheetController class");
		try {
			SaveTimeSheet timeSheet = impl.getTimeSheetDetail(id);
			return new ResponseEntity<SaveTimeSheet>(timeSheet, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<SaveTimeSheet>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/UpdateTimeSheetData")
	public ResponseEntity<String> updateTimeSheet(@RequestBody SaveTimeSheet timesheet, @QueryParam("id") int id) {
		log.info("entered into UpdateTimeSheetData method of HrmsEmpTimeSheetController class");
		try {
			this.impl.updateTimeSheet(timesheet, id);
			return new ResponseEntity<>("Successfully Updated data this id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("data not updated ", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getDetailUsingMonYearWeek")
	public ResponseEntity<TimeSheetResponseForMonthYearWeek> getEmployeeDetailByMonYearandWeek(
			@QueryParam("month") int month, @QueryParam("year") int year, @QueryParam("calweek") int calweek,
			@QueryParam("userId") int userId) {
		log.info("entered into getDetailUsingMonYearWeek method of HrmsEmpTimeSheetController class");
		TimeSheetResponseForMonthYearWeek response = new TimeSheetResponseForMonthYearWeek();
		List<SaveTimeSheet> timesheet = impl.getTimeSheetDetails(month, year, calweek, userId);
		if (timesheet != null) {
			response.setMessage("timesheet detail retrived successfully");
			response.setSaveTimeSheet(timesheet);
			return new ResponseEntity<TimeSheetResponseForMonthYearWeek>(response, HttpStatus.OK);
		} else {
			response.setMessage("timesheet Detail not retrived");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getTimeSheetDetailUsingMon")
	public ResponseEntity<TimeSheetResponseForMonth> getEmployeeDetailByMonYearandWeek(@QueryParam("month") int month,
			@QueryParam("empId") String empId, @QueryParam("year") int year) {
		log.info("entered into getTimeSheetDetailUsingMon method of HrmsEmpTimeSheetController class");
		TimeSheetResponseForMonth response = new TimeSheetResponseForMonth();
		List<Object[]> timesheet = impl.getTimeSheetDetailsByMonth(month, empId, year);
		if (timesheet != null) {
			response.setMessage("timesheet detail retrived successfully");
			response.setList(timesheet);
			return new ResponseEntity<TimeSheetResponseForMonth>(response, HttpStatus.OK);
		} else {
			response.setMessage("timesheet detail not retrived");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

//New Api
	@GetMapping("getTimeSheetDetailsbyUserIdMonYearCalWeekEmpId")
	public ResponseEntity<TimeSheetResponseForMonthYearWeek> getEmployeeDetails(@QueryParam("month") Integer month,
			@QueryParam("empId") String empId, @QueryParam("year") Integer year, @QueryParam("calweek") Integer calweek,
			@QueryParam("userId") Integer userId) {
		log.info("entered into getTimeSheetDetailUsingMon method of HrmsEmpTimeSheetController class");
//		TimeSheetResponseForMonth response = new TimeSheetResponseForMonth();
		TimeSheetResponseForMonthYearWeek response1 = new TimeSheetResponseForMonthYearWeek();
//		if(month!=0 &&  year!=0 && empId!=null) {
		if (empId != null) {
			List<Object[]> timesheet = impl.getTimeSheetDetailsByMonth(month, empId, year);

			if (timesheet != null) {
				response1.setMessage("timesheet detail retrived successfully");
				response1.setList(timesheet);
				return new ResponseEntity<TimeSheetResponseForMonthYearWeek>(response1, HttpStatus.OK);
			} else {
				response1.setMessage("timesheet detail not retrived");
				return new ResponseEntity<>(response1, HttpStatus.NOT_FOUND);
			}

		}
//		else if(calweek!=0 && id !=0 && month!=0 && year!=0 ) {
		else if (calweek != 0) {

			List<SaveTimeSheet> timesheet = impl.getTimeSheetDetails(month, year, calweek, userId);
			if (timesheet != null) {
				response1.setMessage("timesheet detail retrived successfully");
				response1.setSaveTimeSheet(timesheet);
				return new ResponseEntity<TimeSheetResponseForMonthYearWeek>(response1, HttpStatus.OK);
			} else {
				response1.setMessage("timesheet detail not retrived");
				return new ResponseEntity<>(response1, HttpStatus.NOT_FOUND);
			}

		}

		return new ResponseEntity<TimeSheetResponseForMonthYearWeek>(response1, HttpStatus.OK);
	}

	@PostMapping("/getWeekDates")
	public ResponseEntity<CurrentWeekDatesResponse> getWeekDates(@RequestBody CurrentWeekRequest currentWeekRequest) {
		log.info("entered into getWeekDates method of HrmsEmpTimeSheetService class " + currentWeekRequest.getTdate());
		try {
			CurrentWeekDatesResponse good = impl.getWeekDates(currentWeekRequest.getTdate());
			return new ResponseEntity<CurrentWeekDatesResponse>(good, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CurrentWeekDatesResponse>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getEmployeesByReportingManagerId")
	public ResponseEntity<List<EmployeeDetails>> getEmpByRepID(@QueryParam("repId")String repId) {
		try {
		List<EmployeeDetails> ob = impl.getEmpByReportingId(repId);
		return new ResponseEntity<List<EmployeeDetails>>(ob, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<EmployeeDetails>>( HttpStatus.BAD_REQUEST);
		}

	}

//	@GetMapping("/getCalWeekMonthNameByMonthId")
//		public ResponseEntity<List<SaveTimeSheet>> getCalWeekByMonth(@QueryParam("monthId") int month) {
//		log.info("entered into getCalWeekByMonth method of HrmsEmpTimeSheetService class");
//		
//		List<SaveTimeSheet> savetimesheet =impl.getWeekMonthNameByMonthId(month);
//		return new ResponseEntity<List<SaveTimeSheet>>(savetimesheet, HttpStatus.OK);
//
//	}

	@GetMapping("/getDateByYearAndCalWeek")
	public DateResponseTimeSheet getDateByYearAndCalWeek(@QueryParam("year") int year,
			@QueryParam("calWeek") int calWeek) {
		log.info("entered into getDateByYearAndCalWeek method of HrmsEmpTimeSheetController class");
		return impl.dateResponse(year, calWeek);
	}

	@GetMapping("/getEmployeesWhoDidNotAccessTimeSheet")
	public CommonTimeSheetbean getEmployeesWhoDidNotAccessTimeSheet(@QueryParam("year") int year,
			@QueryParam("month") int month) {
		log.info("entered into getEmployeesWhoDidNotAccessTimeSheet method of HrmsEmpTimeSheetController class");
		return impl.getEmployeesWhoDidNotAccessTimeSheet(year, month);
	}

	@GetMapping("/getEmplTimeSheetDetailsByReportingManagerId")
	public TSResponseObj getTimeSheetDetailsByReportingManagerId(@QueryParam("repId")String repId,
			@QueryParam("status") String status) {
		log.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of HrmsEmpTimeSheetController class");
		return impl.getEmplTimeSheetDetailsByReportingManagerId(repId, status);
	}

	@GetMapping("/getProjectIdList")
	public ProjectListResponse getProjectIdList() {
		log.info("entered into getProjectIdList method of HrmsEmpTimeSheetController class");
		return impl.getProjectList();
	}

	@GetMapping("/getEmplDetailsByReportingManagerId")
	public TSResponseObj getEmpDetailsByReportingManagerId(@QueryParam("repId") String repId,
			@QueryParam("calWeek") int calWeek) {
		log.info("entered into getEmpDetailsByReportingManagerId method of HrmsEmpTimeSheetController class");
		return impl.getEmplDetailsByReportingManagerId(repId, calWeek);
	}

	@GetMapping("/getTSDetailsByUserId")
	public TSResponseObj getTSDetailsByUserId(@QueryParam("userId") int userId, @QueryParam("status") String status) {
		log.info("entered into getLeaveStatusByUserId method of HrmsEmpTimeController class");
		return impl.getTSDetailsByUserId(userId, status);
	}

	@GetMapping("/getEmployeeWorkingReportsByUserIdStatusCalweekYear")
	public TSResponseObj getEmployeeWorkingReportsByUserIdStatusCalweekYear(@QueryParam("repId") String repId,
			@QueryParam("status") String status, @QueryParam("calWeek") int calWeek, @QueryParam("year") int year) {
		log.info(
				"entered into getEmployeeWorkingReportsByUserIdStatusCalweekYear method of HrmsEmpTimeSheetController class");
		return impl.getEmployeeWorkingReportsByUserIdStatusCalweekYear(repId, status, calWeek, year);
	}

	@GetMapping("getEmployeeByMangerUsingCalWeekRepIdYearStatusUserIdMon")
	public TSResponseObj getEmployeeByMangerUsingCalWeeikRepIdYearStatusUserId(@QueryParam("repId") String repId,
			@QueryParam("status") String status, @QueryParam("calWeek") Integer calWeek,
			@QueryParam("year") Integer year, @QueryParam("userId") Integer userId,@QueryParam("empId") String empId,@QueryParam("month") Integer month) {
		  if (userId != null && status != null) {
			return impl.getTSDetailsByUserId(userId, status);
		} else if (repId != null && calWeek!= null) {
			return impl.getEmplDetailsByReportingManagerId(repId, calWeek);
		} else if (repId != null && status != null) {
			return impl.getEmplTimeSheetDetailsByReportingManagerId(repId, status);
		}
		  else if (repId != null && status != null && calWeek != null && year != null) {
				return impl.getEmployeeWorkingReportsByUserIdStatusCalweekYear(repId, status, calWeek, year);
			}
		  else if(repId != null && status != null && calWeek != null && year != null && month!=null && empId!=null) {
			  return impl.getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(repId, calWeek, status,  empId,year, month);  
		  }
		return null;
	}
	@GetMapping("/getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus")
	public TSResponseObj getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(@QueryParam("repId") String repId,
			@QueryParam("calWeek") Integer calWeek, @QueryParam("status") String status, @QueryParam("empId") String empId,
			@QueryParam("year")Integer year, @QueryParam("month") Integer month) {
		log.info(
				"entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(repId, calWeek, status,  empId,year, month);
	}
	
	
//	 @PostMapping("/timeSheetApproval")
//	 public ResponseEntity<TimeSheetResponse>
//	 timeSheetApproval(@QueryParam("empId") int empid ,@RequestBody TimeSheetApprovalStatusResponse timeSheetApprovalEntity){
//		 TimeSheetResponse timesheet=impl.timeSheetApproval(empid,timeSheetApprovalEntity);
//	 if(timesheet!=null) {
//	 return new
//	 ResponseEntity<TimeSheetResponse>(timesheet,HttpStatus.ACCEPTED) ;
//	 }else {
//	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	 } 
//	 }

}
