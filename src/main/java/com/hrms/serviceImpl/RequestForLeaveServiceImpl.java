	package com.hrms.serviceImpl;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmailDetails;
import com.hrms.beans.MailStatusResponse;
import com.hrms.controller.EmailController;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;
import com.hrms.entity.EmployeeLeaveTypeEntity;
import com.hrms.entity.LeaveManagementEntity;
import com.hrms.entity.MyLeaveRequestEntity;
import com.hrms.entity.RequestForLeave;
import com.hrms.repository.EmployeeLeaveRequestSummaryRepository;
import com.hrms.repository.EmployeeLeaveTypeRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.ILeaveDetailsRepository;
import com.hrms.repository.IRequestForLeaveRepository;
import com.hrms.repository.LeaveManagementRepository;
import com.hrms.repository.MyLeaveRequestRepository;
import com.hrms.request.bean.EmployeeLeaveTypeBean;
import com.hrms.request.bean.EmployeeLeaveTypeResponseBean;
import com.hrms.request.bean.LeaveDetailsFiltaring;
import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.request.bean.RequestForLeaveBinding;

import com.hrms.request.bean.UpdateEmployeeLeaveDetails;
import com.hrms.request.bean.UpdateLeaveRequest;
import com.hrms.response.bean.Common;
import com.hrms.response.bean.EmpLeaveResponseBean;
import com.hrms.response.bean.EmployeeLeaveResponse;
import com.hrms.response.bean.EntityResponse;
import com.hrms.response.bean.LeaveDetailResponse;
import com.hrms.response.bean.LeaveFilterResponse;
import com.hrms.response.bean.LeaveManagementOptionsResponseBean;
import com.hrms.response.bean.LeavesResponseBean;
import com.hrms.service.IRequestForLeaveService;
import com.hrms.util.HrmsGetDateAndTime;



@Service
public class RequestForLeaveServiceImpl implements IRequestForLeaveService {

	private static final Logger logger=LoggerFactory.getLogger(RequestForLeaveServiceImpl.class);

	@Autowired
	EmployeeLeaveTypeRepository leaveTypeRepo;

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

	@Autowired
	private EmployeeLeaveRequestSummaryRepository leaveRequestRepo;

	@Autowired
	private LeaveManagementRepository leaveManagementRepository;

	@Autowired
	private EmployeeLeaveTypeResponseBean leaveTyperes;

	@Autowired
	private MailStatusResponse mailresponse;

	@Autowired
	private EmailController mailcontroller;

	@Autowired
	private MyLeaveRequestRepository myleaveReqRepo;

	@Autowired
	EmailServiceImpl emailService;





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

	// Leaves Details List
	@Override
	public LeavesResponseBean getLeavesDetails(String user_id, String leavestatus, String view) {
		logger.info("entered into getLeavesDetails of businessClass");			
		List<EmployeeLeaveRequestSummaryEntity>listOfLeaves=new ArrayList<>();		
		int countAll = 0, countPending = 0, countApproved = 0, countRejected = 0, countCancel = 0;

		if (view.equalsIgnoreCase("Employee")) {	

			listOfLeaves.addAll(leaveRequestRepo.findByEmp_id(user_id));

		} else if (view.equalsIgnoreCase("superAdmin")) {
			listOfLeaves.addAll(leaveRequestRepo.listOfLeavesByLeavestatus(leavestatus));

		} else {

			listOfLeaves.addAll(leaveRequestRepo.listOfLeavesByUid(user_id, leavestatus));
		} 
		LeavesResponseBean response = new LeavesResponseBean();	

		if (!listOfLeaves.isEmpty()) {
			response.setMessage("Retrival of Leave Details Successfull.");
			response.setStatus(true);

			if (user_id != null && "Employee".equalsIgnoreCase(view)) {
				for (EmployeeLeaveRequestSummaryEntity leave : listOfLeaves) {
					countAll++;
					if ("Pending for approval".equalsIgnoreCase(leave.getLeaveStatus()))
						countPending++;
					else if ("Approved".equalsIgnoreCase(leave.getLeaveStatus()))
						countApproved++;
					else if ("Rejected".equalsIgnoreCase(leave.getLeaveStatus()))
						countRejected++;
					else if ("Cancel".equalsIgnoreCase(leave.getLeaveStatus()))
						countCancel++;
					else
						logger.info("Nothing to count.");
				}
				response.setCountAll(countAll);
				response.setCountPending(countPending);
				response.setCountApproved(countApproved);
				response.setCountRejected(countRejected);
				response.setCountCancel(countCancel);
			}

			response.setList(listOfLeaves);
		} else {
			response.setMessage("No Leave Details are available.");
			response.setStatus(false);
			response.setList(listOfLeaves);
		}			
		return  response;				
	}


	@Override
	public LeaveManagementOptionsResponseBean leaveManagementOptions() {

		logger.info("entered into getLeaveManagementOptions of businessClass");
		List<LeaveManagementEntity> listOfLeaveManagementOptions = leaveManagementRepository.listOfLeaveManagementOptions();

		LeaveManagementOptionsResponseBean response = new LeaveManagementOptionsResponseBean();

		if (!listOfLeaveManagementOptions.isEmpty()) {
			response.setMessage("Retrival of Leave Management Options Successfull.");
			response.setStatus(true);

			response.setListOfLeaveManagementOptions(listOfLeaveManagementOptions);
		} else {
			response.setMessage("No Leave Management Options are available.");
			response.setStatus(false);
			response.setListOfLeaveManagementOptions(listOfLeaveManagementOptions);
		}
		return response;		

	}

	public EmployeeLeaveTypeResponseBean saveEmployeeLeaveData(EmployeeLeaveTypeBean leaveBean) {
		EmployeeLeaveTypeEntity employeeLeaveEntity = new EmployeeLeaveTypeEntity();
		 Instant timestamp = Instant.now();

		if(leaveTypeRepo.getId(leaveBean.getLeaveType(), leaveBean.getYear())==null) {

			try {
				employeeLeaveEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				employeeLeaveEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				employeeLeaveEntity.setCreatedBy(leaveBean.getEmp_id());
				employeeLeaveEntity.setModifiedBy(leaveBean.getEmp_id());
				employeeLeaveEntity.setLeaveType(leaveBean.getLeaveType());
				employeeLeaveEntity.setNoOfDays(leaveBean.getNoOfDays());
				employeeLeaveEntity.setNoOfDaysMonth(leaveBean.getNoOfDays() / 12);
				employeeLeaveEntity.setYear(leaveBean.getYear());
				employeeLeaveEntity.setLeaveTypeId(leaveBean.getId());
				employeeLeaveEntity.setLeaveType(leaveBean.getLeaveType());

				leaveTypeRepo.save(employeeLeaveEntity);
				// System.out.println(inserted);
				leaveTyperes.setLeaveTypelist(employeeLeaveEntity);
				leaveTyperes.setStatus(true);
				leaveTyperes.setMesssage("leave type saved successfully for the year : "+leaveBean.getYear());
			} 

			catch (Exception e) {
				e.printStackTrace();
			}
		}		
		else {
			leaveTyperes.setLeaveTypelist(null);
			leaveTyperes.setMesssage("already leave type is available for the year : "+leaveBean.getYear());
			leaveTyperes.setStatus(false);
		}		
		return leaveTyperes;

	}




	//updateEmpLeaveReaquest
	@Override
	public MailStatusResponse mailsend(UpdateEmployeeLeaveDetails updateBean, String eid) {
		this.logger.info("Entered update leave status and mail send in service");

		//String mailIdofEmp = this.myleaveReqRepo.mailIdofEmp(eid);
		

		MyLeaveRequestEntity leaveEntity = this.myleaveReqRepo.findByEmpid(eid);
		
		List<String> emailList=new ArrayList<>();
		
		
		String EmpEmail = leaveEntity.getEmail();
		
		emailList.add(EmpEmail);
		
		EmployeeDetails Mangager = this.employeeRepo.findByEmpId(leaveEntity.getReportingManagerId());
		String Manageremail = Mangager.getEmail();
		
		emailList.add(Manageremail);

		EmailDetails mailData=new EmailDetails();

		if(updateBean.getLeaveStatus().equalsIgnoreCase("Approved")) {

			//MyLeaveRequestEntity leaveEntity = this.myleaveReqRepo.findByEmpid(eid);
			leaveEntity.setLeaveStatus("Approved");
			MyLeaveRequestEntity save = this.myleaveReqRepo.save(leaveEntity);
			for(String email:emailList) {
			//EmailDetails mailData=new EmailDetails();
			mailData.setRecipient(email);
			//mailData.setRecipient(mailIdofEmp);
			mailData.setSubject("Applied leave Status");
			mailData.setMsgBody("Hi Mr/Ms "+leaveEntity.getName()+"   your's applied leave request has been  approved");
			String mailMessage = this.emailService.sendSimpleMail(mailData);
			
			//MyLeaveRequestEntity leaveEntity = this.myleaveReqRepo.findByEmp_id(eid);

			if(save!=null) {
				mailresponse.setStatus(true);
				mailresponse.setMessage("Leave Approved and "+mailMessage);	
				this.logger.info("successfully update leave status and sent aprroval mail in service");

			}	
			}
		}else if (updateBean.getLeaveStatus().equalsIgnoreCase("Rejected")){
       
			leaveEntity.setLeaveStatus("Rejected");
			MyLeaveRequestEntity save = this.myleaveReqRepo.save(leaveEntity);
           for(String email:emailList) {
			mailData.setRecipient(email);
			mailData.setSubject("Applied leave Status");
			mailData.setMsgBody("Hi Mr/Ms "+leaveEntity.getName()+"  your's applied leave request has been rejected");
			//mailData.setMsgBody("Levae Approval Canceled");
			//String sendEmail = this.mailcontroller.sendEmail(mailData);
			String mailMessage = this.emailService.sendSimpleMail(mailData);
			mailresponse.setMessage("leave approval rejected and " + mailMessage);
			mailresponse.setStatus(true);
           }
			
			this.logger.info("successfully update leave status and sent rejection mail in service");
		}

		return mailresponse;
	}

	

	//Get Leaves based on year like old hrms
//	@Override
//	public Common getLeavesBasedOnYear(int year) {
//
//	Common common=new Common();
//		List<EmployeeLeaveTypeEntity> leavesBasedOnYear = leaveTypeRepo.getLeavesBasedOnYear(year);
//
//		if (leavesBasedOnYear.size() > 0 && leavesBasedOnYear != null) {
//			common.setMessage("Leaves Information based on year fetched successfully");
//			common.setStatus(true);
//			common.setList(leavesBasedOnYear);
//		} else {
//			common.setMessage("Unable to fetch Leaves Information based on year");
//			common.setStatus(false);
//		}		
//		return common;
//	}

//	@Override
//	public LeavesResponseBean getLeavesByMonth(String view,int id, int month,String leavestatus) {
//		logger.info("entered into getLeavesDetails of RequestForLeaveServiceImpl");
//		int countAll = 0, countPending = 0, countApproved = 0, countRejected = 0, countCancel = 0, countabailLeave = 0;
//		List<LeaveDetailResponse> list = new ArrayList<>();
//		LeavesResponseBean leavesResponseBean = new LeavesResponseBean();
//		List<EmployeeLeaveRequestSummaryEntity> leaveByMonth = leaveRequestRepo.getLeaveByMonth(id, month);
//		
//		if (view.equalsIgnoreCase("Employee")) {
//		
//		if (leaveByMonth != null && leaveByMonth.size() != 0) {
//			for (EmployeeLeaveRequestSummaryEntity ob : leaveByMonth) {
//				LeaveDetailResponse response = new LeaveDetailResponse();
//				response.setAbailLeave(ob.getAppliedLeavesCount());
//				response.setLeaveType(ob.getLeaveType());
//				response.setReason(ob.getReason());
//				response.setFromDate(ob.getFromDate());
//				response.setToDate(ob.getToDate());
//				response.setDays(ob.getDays());
//				response.setStatus(ob.getLeaveStatus());
//				list.add(response);
//				
//				
//			}
//		}
//          else if (view.equalsIgnoreCase("Employee") && leavestatus != null) {
//			
//			List<EmployeeLeaveRequestSummaryEntity> leaveByMonthAndStatus = leaveRequestRepo.getLeaveByMonthAndStatus(id, month, leavestatus);
//			
//			if (leaveByMonthAndStatus != null && leaveByMonthAndStatus.size() != 0) {
//				for (EmployeeLeaveRequestSummaryEntity ob : leaveByMonthAndStatus) {
//					LeaveDetailResponse response = new LeaveDetailResponse();
//					response.setAbailLeave(ob.getAppliedLeavesCount());
//					response.setLeaveType(ob.getLeaveType());
//					response.setReason(ob.getReason());
//					response.setFromDate(ob.getFromDate());
//					response.setToDate(ob.getToDate());
//					response.setDays(ob.getDays());
//					response.setStatus(ob.getLeaveStatus());
//					list.add(response);					
//				}				
//			}			
//	}
//		//////
//			if (!list.isEmpty()) {
//				leavesResponseBean.setMessage("Retrival of Leave Details Successfull.");
//				leavesResponseBean.setStatus(true);		
//				
//				if (id != 0 && "Employee".equalsIgnoreCase(view) && leavestatus != null) {
//					
//					for (EmployeeLeaveRequestSummaryEntity leave : leaveByMonth) {
//						
//						countAll++;
//						if ("Pending for approval".equalsIgnoreCase(leave.getLeaveStatus()))
//							countPending++;
//						else if ("Approved".equalsIgnoreCase(leave.getLeaveStatus()))
//							countApproved++;
//						else if ("Rejected".equalsIgnoreCase(leave.getLeaveStatus()))
//							countRejected++;
//						else if ("Cancel".equalsIgnoreCase(leave.getLeaveStatus()))
//							countCancel++;
//						// else if ("abailLeave".equals(leave.getAbailLeave()))
//						// countabailLeave++;
//						else
//							logger.info("Nothing to count.");
//					}
//					leavesResponseBean.setCountAll(countAll);
//					leavesResponseBean.setCountPending(countPending);
//					leavesResponseBean.setCountApproved(countApproved);
//					leavesResponseBean.setCountRejected(countRejected);
//					leavesResponseBean.setCountCancel(countCancel);
//					// response.setAblLeave(countabailLeave);
//						
//					}
//				leavesResponseBean.setList(leaveByMonth);
//				}
//			else {
//				leavesResponseBean.setMessage("No Leave Details are available.");
//				leavesResponseBean.setStatus(false);
//				leavesResponseBean.setList(leaveByMonth);
//			}			
//			
//			}
//		return leavesResponseBean;			
//	}

//	@Override
//	public List<EmployeeLeaveResponse> getLeaveDataByReqIdDate(int reqId, String date) {
//		
//		List<EmployeeLeaveResponse> listOfLeavedEmployees = new ArrayList<EmployeeLeaveResponse>();
//		EmployeeLeaveResponse employeeLeaveResp = new EmployeeLeaveResponse();
//		if (reqId == 0 && date != null) {
//			List<EmployeeLeaveRequestSummaryEntity> leaveDataByRepIdDate = leaveRequestRepo.getLeaveDataByRepIdDate(date);
//			if (leaveDataByRepIdDate != null && leaveDataByRepIdDate.size() != 0) {
//				System.out.println("sadfas");
//				for (EmployeeLeaveRequestSummaryEntity ob : leaveDataByRepIdDate) {				
//					
//					employeeLeaveResp.setEmployeeName(ob.getUserName());
//					employeeLeaveResp.setLeaveFromDate(ob.getFromDate());
//					employeeLeaveResp.setLeaveToDate(ob.getToDate());
//					employeeLeaveResp.setUserId(ob.getUser_id());	                
//					listOfLeavedEmployees.add(employeeLeaveResp);
//					
//				}
//			}
//			
//		}
//		else if (reqId != 0 && date != null) {
//			List<EmployeeLeaveRequestSummaryEntity> findApprovedLeaveRequests = leaveRequestRepo.findApprovedLeaveRequests(reqId, date);
//			
//			if (findApprovedLeaveRequests != null && findApprovedLeaveRequests.size() != 0) {
//				
//				for (EmployeeLeaveRequestSummaryEntity ob : findApprovedLeaveRequests) {
//					employeeLeaveResp.setEmployeeName(ob.getUserName());
//					employeeLeaveResp.setLeaveFromDate(ob.getFromDate());
//					employeeLeaveResp.setLeaveToDate(ob.getToDate());
//					employeeLeaveResp.setUserId(ob.getUser_id());	                
//					listOfLeavedEmployees.add(employeeLeaveResp);
//					
//				}				
//			}			
//		}
//		
//		return listOfLeavedEmployees;
//		
//		
//	}

	@Override
	public EmpLeaveResponseBean updateAllLeaveSummary(UpdateLeaveRequest updateBean) {
		MyLeaveRequestEntity entityBean = new MyLeaveRequestEntity();
		EmpLeaveResponseBean updateResponseBean = new EmpLeaveResponseBean();
		 Instant timestamp = Instant.now();
		
		List<LeaveRequestBean> leaveBean = updateBean.getLeaveBean();
		 Date date = new Date();
		entityBean.setModifiedDate(timestamp);
		entityBean.setModifiedDate(timestamp);
		
		for (LeaveRequestBean value : leaveBean) {
			BeanUtils.copyProperties(entityBean, value);
			
			
		}
		
		return updateResponseBean;
	}

	@Override
	public LeavesResponseBean getLeaveDetailsForManager( int managerid, String leavestatus,String emp_id) {
		
		List<EmployeeLeaveRequestSummaryEntity>listOfLeaves=new ArrayList<>();	
		LeavesResponseBean response = new LeavesResponseBean();	
		
		//listOfLeaves.addAll( leaveRequestRepo.findByReportingManagerIdAndLeaveStatus(managerid, leavestatus));
	//	listOfLeaves.addAll(leaveRequestRepo.findByRepManId(managerid, leavestatus));
		
		if(null!=emp_id && !"".equals(emp_id)) {
		System.out.println("inside method");
			
		  listOfLeaves.addAll(leaveRequestRepo.findByEmpId(emp_id));
		}
		if(leavestatus!=null && !"".equals(leavestatus)) {
			listOfLeaves.addAll(leaveRequestRepo.findByLeaveStatus(leavestatus));
			
		}
	//listOfLeaves.addAll(leaveRequestRepo.findAll());
		
		if(!listOfLeaves.isEmpty()) {
			response.setMessage("Retrival of Leave Details Successfull.");
			response.setStatus(true);
			response.setList(listOfLeaves);
		}
		else {
			response.setMessage("No Leave Details are available.");
			response.setStatus(false);
			response.setList(listOfLeaves);			
		}		
		
		return response;
		
	}

	@Override
	public List<MyLeaveRequestEntity> getLeaveHistoryByConditions(int year,int month,String status) {
		
		MyLeaveRequestEntity entity=new MyLeaveRequestEntity();
		LeaveFilterResponse filterResponse=new LeaveFilterResponse();
		List<MyLeaveRequestEntity> list=new ArrayList<>();
		
		if(year!=0 && !"".equals(year)) {
			List<MyLeaveRequestEntity> leavaesByYear = myleaveReqRepo.getLeavaesByYear(year);		
			filterResponse.setYear(year);			
		 //	return leavaesByYear;
		}
		if(month!=0 && !"".equals(month)) {			
			List<MyLeaveRequestEntity> leavesByMonth = myleaveReqRepo.getLeavesByMonth(month);
			//return leavesByMonth;			
		}
		if(status!=null && !"".equals(status)) {
			List<MyLeaveRequestEntity> leavesByStatus = myleaveReqRepo.getLeavesByStatus(status);
		//	return leavesByStatus;			
		}		
		//return myleaveReqRepo.findAll();
		return null;
	}	
}
