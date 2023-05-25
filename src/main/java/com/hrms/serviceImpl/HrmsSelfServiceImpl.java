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
	private  EmployeeLeaveDetailsRepository leaveDetailsRepo;
	
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
	public CommonResponseBean saveLeaveRequest(LeaveRequestBean reqBean, int roleId, int menuId) {
		//EmpLeaveResponseBean empLeaveResponse= new EmpLeaveResponseBean();
		MyLeaveRequestEntity reqEntity = new MyLeaveRequestEntity();
		CommonResponseBean commonRes= new CommonResponseBean();	
		EmployeeLeaveRequestSummaryEntity leaveSummery=new EmployeeLeaveRequestSummaryEntity();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDate = LocalDate.parse(reqBean.getFromDate(), formatter);
		
		LocalDate toDate = LocalDate.parse(reqBean.getToDate(), formatter);
		
		
		if (myLeaveReqRepo.findLeaveTypeIdByDatesAndUserId(fromDate,toDate ,reqBean.getEmpId())!=null) {
			

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
			
			
			
			EmployeeDetails empDetails = employeeRepo.findByEmpId(reqBean.getEmpId());
			int id = empDetails.getId();
			EmployeeDetails empDetails1 = employeeRepo.findById(id).get();
	
			reqEntity.setEmp_id(empDetails1.getEmpId());
			reqEntity.setLeaveTypeId(reqBean.getLeaveTypeId());
			reqEntity.setLeaveType(reqBean.getLeaveType());
			reqEntity.setReason(reqBean.getReason());
			reqEntity.setFromDate(fromDate);
			reqEntity.setToDate(toDate);
			reqEntity.setLeaveFor(reqBean.getLeaveFor());
			reqEntity.setLeaveStatus("Pending for approval");
			reqEntity.setDays(days);
			reqEntity.setReportingManagerId(empDetails1.getReportingManagerId());;
			reqEntity.setReportingManager(empDetails1.getReportingManager());
			reqEntity.setHrId(empDetails1.getHrManagerId());
			reqEntity.setAvailableLeaves(leaveTypeRepo.getNoOfDays()-leaveReqSummery.getNoOfDaysApproved(reqBean.getEmpId()));
			reqEntity.setIsactive(1);
			reqEntity.setModifiedDate(dateTimeUtility.GetUTCdatetimeAsString());
			reqEntity.setCreatedDdate(dateTimeUtility.GetUTCdatetimeAsString());
			reqEntity.setCreatedBy(empDetails1.getUserId());
			reqEntity.setModifiedBy(empDetails1.getUserId());
			reqEntity.setAppliedLeavescount(reqBean.getAppliedLeavesCount());
			reqEntity.setEmail(empDetails1.getEmail());
			reqEntity.setName(empDetails1.getFirstName());
			
			myLeaveReqRepo.save(reqEntity);
			
			leaveSummery.setLeaveRequestId(id);
			leaveSummery.setEmp_id(empDetails1.getEmpId());
			leaveSummery.setUserName(empDetails1.getFirstName());
			leaveSummery.setDepartmentId(empDetails1.getDepartmentId());
			leaveSummery.setLeaveStatus(reqBean.getLeaveStatus());
			leaveSummery.setDepartmentName(empDetails1.getDepartmentName());
			leaveSummery.setAppliedLeavesCount(reqEntity.getAppliedLeavescount());
			leaveSummery.setBusinessUnitId(empDetails1.getBusinessunitId());
			leaveSummery.setBusinessUnitName(empDetails1.getBusinessunitName());
			//leaveSummery.getReason(reqBean.getReason());
			leaveSummery.setApproverComments("not seen");
			leaveSummery.setLeaveTypeId(reqBean.getLeaveTypeId());
			leaveSummery.setLeaveTypeName(reqBean.getLeaveType());
			leaveSummery.setFromDate(fromDate);
			leaveSummery.setToDate(toDate);
			leaveSummery.setReportingManagerId(empDetails1.getReportingManagerId());
			leaveSummery.setReportingManagerName(empDetails1.getReportingManager());
			leaveSummery.setHrId(empDetails1.getHrManagerId());
			leaveSummery.setAppliedLeavesCount(days);
			leaveSummery.setCreatedBy(empDetails1.getUserId());
			leaveSummery.setModifiedBy(empDetails1.getUserId());
		
			leaveReqSummery.save(leaveSummery);
			
			leaveBlogic.updateEmployeeLeaves(reqBean.getLeaveType(), empDetails1.getEmpId(), days, "save", null);
			
			
			}
			catch (Exception e) {
				e.printStackTrace();;
			}
			
			// pending for naresh mail
			
			
			commonRes.setMessage("the leave applied successfully wait for approval");
			commonRes.setStatus(true);
			
			
		}
		else {
			commonRes.setMessage("the leave is already applied on that date");
			commonRes.setStatus(false);
		}
		
		
		
		
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
