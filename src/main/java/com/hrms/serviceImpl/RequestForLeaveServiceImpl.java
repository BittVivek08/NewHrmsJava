package com.hrms.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hrms.entity.EmployeeDetails;

import com.hrms.entity.RequestForLeave;
import com.hrms.service.IRequestForLeaveService;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.ILeaveDetailsRepository;
import com.hrms.repository.IRequestForLeaveRepository;

import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;


import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import java.util.List;

@Service
public class RequestForLeaveServiceImpl implements IRequestForLeaveService {

	private static final Logger logger=LoggerFactory.getLogger(RequestForLeaveServiceImpl.class);
	
	@Autowired
	HolidayCalenderRepository holidayRepo;

	@Autowired
	private IRequestForLeaveRepository reqLeaveRepo;

	@Autowired
	private ILeaveDetailsRepository leaveRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private EntityResponse response;
	
//	@Autowired
//	private LeaveRequestEntityRepo leaveRequestEntiryRepo;

	@Override
	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details) {

		// convert RequestForLeaveBinding obj to RequestForLeave Obj
		RequestForLeave leave = new RequestForLeave();
		// leave.setId((details.getId()));
		leave.setLeaveType(details.getLeaveType());
		leave.setReason(details.getReason());
		// leave.setMappingId(details.getMappingId());
		leave.setStatus("Panding");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(details.getStartDate(), formatter);
		leave.setStartDate(startDate);
		LocalDate endDate = LocalDate.parse(details.getEndDate(), formatter);
		leave.setEndDate(endDate);

		// adding Holidays
		List<LocalDate> holidays = new ArrayList<>();
		holidays.addAll(holidayRepo.finDates());

		Predicate<LocalDate> isHoliday = holidays::contains;
		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

		// Days between startDate inclusive and endDate exclusive
		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		int totaldays = (int) daysBetween + 1;
		System.out.println("Days between = " + totaldays);

//	       List<LocalDate> workDays = Stream.iterate(startDate, date -> date.plusDays(1))
//	               .limit(totaldays)
//	               .filter(isHoliday.or(isWeekend).negate())
//	               .toList();

		List<LocalDate> workDays = Stream.iterate(startDate, date -> date.plusDays(1)).limit(totaldays)
				.filter(isHoliday.or(isWeekend).negate()).collect(Collectors.toList());

		long actualDaysBetween = workDays.size();
		// System.out.println("Actual days between = " + actualDaysBetween);
		int reqDays = (int) actualDaysBetween;
		leave.setTotalLeaveDays(reqDays);

		// sick and casual leave
		Integer avalCasualLeave = null;
		Integer avalSickLeave = null;

		// leave applied condition
		if (details.getLeaveType().equalsIgnoreCase("casual")) {
			// check avalCasualLeave is null or not
			if (reqLeaveRepo.minCasualLeave(details.getEmp_id()) == null)
				avalCasualLeave = leaveRepo.getTotalLeave("casual") - reqDays;
			else
				avalCasualLeave = reqLeaveRepo.minCasualLeave(details.getEmp_id()) - reqDays;
		} else {
			if (reqLeaveRepo.minCasualLeave(details.getEmp_id()) == null)
				avalCasualLeave = leaveRepo.getTotalLeave("casual");
			else
				avalCasualLeave = reqLeaveRepo.minCasualLeave(details.getEmp_id());
		}

		// check avalSickLeave is null or not
		if (details.getLeaveType().equalsIgnoreCase("sick")) {
			if (reqLeaveRepo.minSickLeave(details.getEmp_id()) == null)
				avalSickLeave = leaveRepo.getTotalLeave("sick") - reqDays;
			else
				avalSickLeave = reqLeaveRepo.minSickLeave(details.getEmp_id()) - reqDays;
		} else {
			if (reqLeaveRepo.minSickLeave(details.getEmp_id()) == null)
				avalSickLeave = leaveRepo.getTotalLeave("sick");
			else
				avalSickLeave = reqLeaveRepo.minSickLeave(details.getEmp_id());
		}

		// set final leave
		leave.setAvalCasualLeave(avalCasualLeave);
		leave.setAvalSickLeave(avalSickLeave);

		EmployeeDetails empDetails = employeeRepo.findByEmpId(details.getEmp_id());
		int id = empDetails.getId();

		EmployeeDetails empDetails1 = employeeRepo.findById(id).get();
		leave.setEmp_id(empDetails1.getEmpId());

		// leave.setEmpid(empDetails.getEmpId());

		LocalDate currentDate = LocalDate.now();
		// save
		if (avalCasualLeave >= 0 && avalCasualLeave <= leaveRepo.getTotalLeave("casual") && avalSickLeave >= 0
				&& avalSickLeave <= leaveRepo.getTotalLeave("sick") && (currentDate.isBefore(startDate) == true)) {
			reqLeaveRepo.save(leave);

			response.setMsg("Done with your leave");
			response.setStatus(true);
		} else {
			response.setMsg("check your leave days");
			response.setStatus(false);
		}

		return response;
	}

	
//	// Leaves Details List
//	@Override
//	public List<LeaveRequestEntity> getLeavesDetails(String user_id, String leavestatus, String view) {
//		
//		
//		logger.info("entered into getLeavesDetails of businessClass");
//		
//		
//		
//		List<LeaveRequestEntity>listOfLeaves=new ArrayList<>();
//		
//		int countAll = 0, countPending = 0, countApproved = 0, countRejected = 0, countCancel = 0;
//		if (leavestatus.equalsIgnoreCase("pendingforapproval")) {
//			leavestatus = "pending for approval";
//		}
//		
//		if (view.equalsIgnoreCase("Employee")) {
//			
//			listOfLeaves.addAll(leaveRequestEntiryRepo.findByEmp_id(user_id));
//			
//		} else if (view.equalsIgnoreCase("superAdmin")) {
//			listOfLeaves.addAll(leaveRequestEntiryRepo.listOfLeavesByLeavestatus(leavestatus));
//			
//		} else {
//					
//			listOfLeaves.addAll(leaveRequestEntiryRepo.listOfLeavesByUid(user_id, leavestatus));
//		} 
//		LeavesResponseBean response = new LeavesResponseBean();	
//		
//		if (!listOfLeaves.isEmpty()) {
//			response.setMessage("Retrival of Leave Details Successfull.");
//			response.setStatus(true);
//
//			if (user_id != null && "Employee".equalsIgnoreCase(view)) {
//				for (LeaveRequestEntity leave : listOfLeaves) {
//					countAll++;
//					if ("Pending for approval".equalsIgnoreCase(leave.getLeaveStatus()))
//						countPending++;
//					else if ("Approved".equalsIgnoreCase(leave.getLeaveStatus()))
//						countApproved++;
//					else if ("Rejected".equalsIgnoreCase(leave.getLeaveStatus()))
//						countRejected++;
//					else if ("Cancel".equalsIgnoreCase(leave.getLeaveStatus()))
//						countCancel++;
//					else
//						logger.info("Nothing to count.");
//
//				}
//				response.setCountAll(countAll);
//				response.setCountPending(countPending);
//				response.setCountApproved(countApproved);
//				response.setCountRejected(countRejected);
//				response.setCountCancel(countCancel);
//			}
//
//			response.setListOfLeaves(listOfLeaves);
//		} else {
//			response.setMessage("No Leave Details are available.");
//			response.setStatus(false);
//			response.setListOfLeaves(listOfLeaves);
//		}	
//		
//		
//	//	return (List<LeaveRequestEntity>) Response.status(Response.Status.OK).entity(response).build();
//	//	return (List<LeaveRequestEntity>) response;
//		return null;
//				
//	}
}
