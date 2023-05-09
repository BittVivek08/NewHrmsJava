package com.hrms.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.RequestForLeave;
import com.hrms.service.IRequestForLeaveService;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ILeaveDetailsRepository;
import com.hrms.repository.IRequestForLeaveRepository;
import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.List;

@Service
public class RequestForLeaveServiceImpl implements IRequestForLeaveService {

	@Autowired
	private IRequestForLeaveRepository reqLeaveRepo;
	
	@Autowired
	private ILeaveDetailsRepository leaveRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private EntityResponse response;
	
	@Override
	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details) {
		
		//convert RequestForLeaveBinding obj to RequestForLeave Obj
		RequestForLeave leave=new RequestForLeave();
		//leave.setId((details.getId()));
		leave.setLeaveType(details.getLeaveType());
		leave.setReason(details.getReason());
		leave.setMappingId(details.getMappingId());
		leave.setStatus("Panding");
		
		//holiday
		String dateString3="2023-04-09";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate=LocalDate.parse(details.getStartDate(), formatter);
		leave.setStartDate(startDate);
		LocalDate endDate=LocalDate.parse(details.getEndDate(), formatter);
		leave.setEndDate(endDate);
		 LocalDate holiday1=LocalDate.parse(dateString3,formatter);
		
		 List<LocalDate> holidays = new ArrayList<>();
	       holidays.add(startDate);
	       holidays.add(holiday1);
	      // holidays.add(LocalDate.of(date1));

	       Predicate<LocalDate> isHoliday = holidays::contains;
	       Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
	               || date.getDayOfWeek() == DayOfWeek.SUNDAY;

	       // Days between startDate inclusive and endDate exclusive
	       long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	       int totaldays=(int) daysBetween + 1;
	       System.out.println("Days between = " + totaldays);

	       List<LocalDate> workDays = Stream.iterate(startDate, date -> date.plusDays(1))
	               .limit(totaldays)
	               .filter(isHoliday.or(isWeekend).negate())
	               .toList();

	       long actualDaysBetween = workDays.size();
	     //  System.out.println("Actual days between = " + actualDaysBetween);
		int reqDays=(int)actualDaysBetween;
		leave.setTotalLeaveDays(reqDays);
		
		//sick and casual leave
		Integer avalCasualLeave = null;
		Integer avalSickLeave = null;
		
		//leave applied condition
		if(details.getLeaveType().equalsIgnoreCase("casual")) {
			//check avalCasualLeave is null or not
			if(reqLeaveRepo.minCasualLeave(details.getMappingId())==null)
				avalCasualLeave=leaveRepo.getTotalLeave("casual")-reqDays;
			else
				avalCasualLeave=reqLeaveRepo.minCasualLeave(details.getMappingId())-reqDays;
		}
		else{
			if(reqLeaveRepo.minCasualLeave(details.getMappingId())==null)
				avalCasualLeave=leaveRepo.getTotalLeave("casual");
			else
				avalCasualLeave=reqLeaveRepo.minCasualLeave(details.getMappingId());
		}
		
		
		//check avalSickLeave is null or not
		if(details.getLeaveType().equalsIgnoreCase("sick")){
			if(reqLeaveRepo.minSickLeave(details.getMappingId())==null)
				avalSickLeave=leaveRepo.getTotalLeave("sick")-reqDays;
			else
				avalSickLeave=reqLeaveRepo.minSickLeave(details.getMappingId())-reqDays;
		}
		else{
			if(reqLeaveRepo.minSickLeave(details.getMappingId())==null)
				avalSickLeave=leaveRepo.getTotalLeave("sick");
			else
				avalSickLeave=reqLeaveRepo.minSickLeave(details.getMappingId());
		}
		
		//set final leave 
		leave.setAvalCasualLeave(avalCasualLeave);
		leave.setAvalSickLeave(avalSickLeave);
			
			EmployeeDetails empDetails=employeeRepo.findById(details.getMappingId()).get();
			leave.setDetails(empDetails);
			
			//save
			if(avalCasualLeave>=0 && avalCasualLeave<=leaveRepo.getTotalLeave("casual") && avalSickLeave>=0 && avalSickLeave<=leaveRepo.getTotalLeave("sick")) {
				reqLeaveRepo.save(leave);
		
				response.setMsg("Done with your leave");
				response.setStatus(true);
			}
			else {
				response.setMsg("check your leave days");
				response.setStatus(false);
			}
		
		return response;
	}

}
