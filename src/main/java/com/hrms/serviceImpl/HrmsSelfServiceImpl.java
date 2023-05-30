package com.hrms.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;
import com.hrms.entity.LeaveRequestEntity;
import com.hrms.entity.MyLeaveRequestEntity;
import com.hrms.entity.Privileges;
import com.hrms.repository.EmployeeLeaveDetailsRepository;
import com.hrms.repository.EmployeeLeaveRequestSummaryRepository;
import com.hrms.repository.EmployeeLeaveTypeRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.LeaveRequestRepository;
import com.hrms.repository.MyLeaveRequestRepository;
import com.hrms.repository.PrivilegesRepo;
import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.LeaveResponseBean;
import com.hrms.service.IHrmsSelfService;
import com.hrms.util.HrmsGetDateAndTime;
import com.hrms.util.LeaveRequestBLogic;

import ch.qos.logback.classic.Logger;

@Service
public class HrmsSelfServiceImpl implements IHrmsSelfService {
	
	private static final Logger logger=(Logger) LoggerFactory.getLogger(HrmsSelfServiceImpl.class);
	
	@Autowired
	private MyLeaveRequestRepository myLeaveReqRepo;
	
	@Autowired
	private LeaveRequestRepository leaveRequestRepo;
	
	@Autowired
	private PrivilegesRepo privilegeRepo;
	
	@Autowired
	private  HolidayCalenderRepository holidayRepo;
	
	@Autowired
	private   EmployeeLeaveTypeRepository leaveTypeRepo;
	
	@Autowired
	private HrmsGetDateAndTime dateTimeUtility;
	
	@Autowired
	private LeaveRequestBLogic leaveBlogic;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
    private EmployeeLeaveRequestSummaryRepository leaveReqSummery;
	
	@Override
	public CommonResponseBean  saveLeaveRequest(LeaveRequestBean reqBean ,
			String emp_id ,String leaveType) {
			 
		//EmpLeaveResponseBean empLeaveResponse= new EmpLeaveResponseBean();
		MyLeaveRequestEntity reqEntity = new MyLeaveRequestEntity();
		CommonResponseBean commonRes= new CommonResponseBean();	
		EmployeeLeaveRequestSummaryEntity leaveSummery=new EmployeeLeaveRequestSummaryEntity();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDate = LocalDate.parse(reqBean.getFromDate(), formatter);
		
		LocalDate toDate = LocalDate.parse(reqBean.getToDate(), formatter);
		
		
		
			if(myLeaveReqRepo.checkMatchingDates(emp_id, toDate, fromDate)==false) {

			try {
			// adding Holidays
			List<LocalDate> holidays = new ArrayList<>();
			holidays.addAll(holidayRepo.finDates());

			Predicate<LocalDate> isHoliday = holidays::contains;
			Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
					|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

			// Days between startDate inclusive and endDate exclusive
			long daysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
			int totaldays = (int) daysBetween + 1;
			System.out.println("Days between = " + totaldays);


			List<LocalDate> workDays = Stream.iterate(fromDate, date -> date.plusDays(1)).limit(totaldays)
					.filter(isHoliday.or(isWeekend).negate()).collect(Collectors.toList());

			long actualDaysBetween = workDays.size();
			float days = (float) actualDaysBetween;
			
			
			EmployeeDetails empDetails = employeeRepo.findByEmpId(emp_id);
			
			
		reqEntity.setAvailabelDays(leaveTypeRepo.getNoOfDays(leaveType)-leaveReqSummery.getNoOfDaysApproved(emp_id,leaveType));
			
			
			reqEntity.setEmp_id(empDetails.getEmpId());
			reqEntity.setLeaveType(leaveType);
			reqEntity.setReason(reqBean.getReason());
			reqEntity.setFromDate(fromDate);
			reqEntity.setToDate(toDate);
			reqEntity.setLeaveStatus("Pending for approval");
			reqEntity.setDays(days);
			reqEntity.setReportingManagerId(empDetails.getReportingManagerId());;
			reqEntity.setReportingManager(empDetails.getReportingManager());
			reqEntity.setHrId(empDetails.getHrManagerId());
			reqEntity.setIsactive(1);
			reqEntity.setModifiedDate(dateTimeUtility.GetUTCdatetimeAsString());
			reqEntity.setCreatedDdate(dateTimeUtility.GetUTCdatetimeAsString());
			reqEntity.setCreatedBy(empDetails.getUserId());
			reqEntity.setModifiedBy(empDetails.getUserId());
			reqEntity.setEmail(empDetails.getEmail());
			reqEntity.setName(empDetails.getFirstName());
			myLeaveReqRepo.save(reqEntity);
			
			
			leaveSummery.setEmp_id(empDetails.getEmpId());
			
			leaveSummery.setUser_id(empDetails.getUserId());
			leaveSummery.setDepartmentId(empDetails.getDepartmentId());
			leaveSummery.setLeaveStatus("pending");
			leaveSummery.setDepartmentName(empDetails.getDepartmentName());
			leaveSummery.setBusinessUnitId(empDetails.getBusinessunitId());
			leaveSummery.setBusinessUnitName(empDetails.getBusinessunitName());
			leaveSummery.setReason(reqBean.getReason());
			leaveSummery.setApproverComments("not seen");
			leaveSummery.setLeaveType(leaveType);
			leaveSummery.setFromDate(fromDate);
			leaveSummery.setToDate(toDate);
			leaveSummery.setReportingManagerId(empDetails.getReportingManagerId());
			leaveSummery.setReportingManagerName(empDetails.getReportingManager());
			leaveSummery.setHrId(empDetails.getHrManagerId());
			leaveSummery.setCreatedBy(33);
			leaveSummery.setModifiedBy(32);	
			leaveSummery.setUser_id(empDetails.getUserId());
			leaveReqSummery.save(leaveSummery);
						
			leaveBlogic.updateEmployeeLeaves(leaveType ,emp_id, days, "save", null);
			
			
			}
			catch (Exception e) {
			  e.printStackTrace();
			}
			
			// pending for naresh mail
			
			commonRes.setMessage("the leave applied successfully wait for approval");
			commonRes.setStatus(true);
			}
			else {
				commonRes.setMessage("the leave is already applied on that date");
				commonRes.setStatus(false);			}
			
		
		
		
		
		
		return commonRes;
	}

	// selfService->Leaves->MyLeaves->Delete
		public LeaveResponseBean deleteMyLeave(int id) {
			logger.info("entered into deleteMyLeaveDetails method of business class");
			LeaveResponseBean responseBean = new LeaveResponseBean();
			try {
				myLeaveReqRepo.deleteById(id);
				responseBean.setMessage("Deleted Succesfully::");
				responseBean.setStatus(true);
				//return Response.status(Response.Status.OK).entity(responseBean).build();
				return responseBean;
			} catch (Exception e) {
				logger.info("catch block of deleteMyLeaveDetails of business class::" + e);
				responseBean.setMessage("something went wrong ::");
				responseBean.setStatus(false);
			//	return Response.status(Response.Status.OK).entity(responseBean).build();
				return responseBean;
			}
		}
	@Override
	public Response fetchAppliedLeaveData(int userId, int roleId, int menuId) {
		
		return null;
	}

	@Override
	public Response employeetotalleave(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseBean getHistoryOfAppliedLeaveDetails(String emp_id, int roleId, int menuId) {
		logger.info("entered into getHistoryOfAppliedLeaveDetails method of business class");
		CommonResponseBean response = new CommonResponseBean();
		List<LeaveRequestEntity> fetchAppliedLeaveRequest = leaveRequestRepo.fetchAppliedLeaveRequest(emp_id);
				
		List<Privileges> listOfPrivilleges = privilegeRepo.getPrivileges(roleId, menuId);
		if (!fetchAppliedLeaveRequest.isEmpty()) {
			response.setMessage("History of applied leave Details Retrieved Successfully.");
			response.setStatus(true);
			response.setList(fetchAppliedLeaveRequest);
			response.setPrivilleges(listOfPrivilleges);
		} else {
			response.setMessage("Failed to Retrieved applied  Leaves History .");
			response.setStatus(false);
		}
		return response;		
		//return null;
	}

	@Override
	public CommonResponseBean totalLeaveTaken(int id) {
		
		CommonResponseBean commonResponse = new CommonResponseBean();
		List<LeaveRequestEntity> totalLeaveTaken = leaveRequestRepo.totalLeaveTaken(id);
		if (totalLeaveTaken != null) {
			commonResponse.setMessage("Successfully");
			commonResponse.setStatus(true);
			commonResponse.setList(totalLeaveTaken);
		} else {
			commonResponse.setMessage("UnSuccessfully");
			commonResponse.setStatus(false);
		}
		
		
		return commonResponse;
	}

}
