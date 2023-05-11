package com.hrms.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;


import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.RequestForLeave;
//import com.hrms.entity.EmployeeWorkStatusEntity;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.repository.ClientDetailsRepository;
//import com.hrms.entity.ProjectTaskEntity;
//import com.hrms.entity.SavingTimeSheet;
//import com.hrms.entity.Task;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.ILeaveDetailsRepository;
import com.hrms.repository.IRequestForLeaveRepository;
import com.hrms.repository.ProjectDetailsRepository;
//import com.hrms.repository.EmployeeWorkStatusRepo;
import com.hrms.repository.SaveTimeSheetRepo;
import com.hrms.repository.TaskDeatailsRepository;
import com.hrms.responsebean.TimeSheetResponseForMonthYearWeek;
import com.hrms.service.TimeSheetDetails;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
//@ComponentScan({"com.hrms.repository.EmployeeRepository","com.hrms.entity.TimeSheetApprovalStatus"})
public class TimeSheetDetailsImpl implements TimeSheetDetails {

	@Autowired
	private  EntityBeanResponse  entityBeanResponse;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SaveTimeSheetRepo hrmsEntityRepo;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository; 
	@Autowired
    private ProjectDetailsRepository pojectDetailsRepository;
	@Autowired
	private TaskDeatailsRepository taskDetailsRepository ;
    @Autowired
    private HolidayCalenderRepository holidayCalenderRepository;
    @Autowired
    private ILeaveDetailsRepository ILeaveDetailsRepository;
    @Autowired
    private TimeSheetResponseForMonthYearWeek timeSheetResponseForMonthYearWeek;
    @Autowired
    private IRequestForLeaveRepository iRequestForLeaveRepository;
	public TimeSheetResponseForMonthYearWeek saveTimeSheett(SaveTimeSheet savetimesheet) {
		  LocalDate date1=iRequestForLeaveRepository.findByStartDate(LocalDate.now());
		  LocalDate date=holidayCalenderRepository.findByDate1(LocalDate.now());
//	  	  String leave=iRequestForLeaveRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
//		  && leave!=savetimesheet.getEmp().getEmpId()
          if(date==null && date1==null ) {
		
			int cal=Integer.parseInt(savetimesheet.getFri_hours())+Integer.parseInt(savetimesheet.getMon_hours())+Integer.parseInt(savetimesheet.getSat_hours())+Integer.parseInt(savetimesheet.getThurs_hours())+Integer.parseInt(savetimesheet.getSun_hours())+Integer.parseInt(savetimesheet.getTue_hours())+Integer.parseInt(savetimesheet.getWed_hours());
			savetimesheet.setTotalWeekHours(String.valueOf(cal));
			savetimesheet.setStatus("pending");
			EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
			ClientDetailsEntity client = this.clientDetailsRepository.findByClientid(savetimesheet.getClient().getClientid());
			ProjectDetailsEntity proj = this.pojectDetailsRepository.findByProjectId(savetimesheet.getProj().getProjectId());
			TaskDetailsEntity task = this.taskDetailsRepository.getById(savetimesheet.getTask().getTaskid());
			savetimesheet.setTask(task);
			savetimesheet.setProj(proj);
			savetimesheet.setClient(client);
			savetimesheet.setEmp(emp1);
			savetimesheet.setCreated_Date(LocalDateTime.now());
			SaveTimeSheet  result=  this.hrmsEntityRepo.save(savetimesheet);
			timeSheetResponseForMonthYearWeek.setMessage("successfully added data ");
			timeSheetResponseForMonthYearWeek.setSaveTimeSheet(result);
			return timeSheetResponseForMonthYearWeek;
           }else {
        	   timeSheetResponseForMonthYearWeek.setMessage("today is leave/holiday you what to enter data to click yes/no");
//             savetimesheet.getEmp().getEmpId()==leave
        	   
                 if(date==null  || date1==LocalDate.now() ||date==LocalDate.now() || date1==null ){
            	  
            	   if(savetimesheet.getRequest().equalsIgnoreCase("yes")) {
            		   
            	   int cal=Integer.parseInt(savetimesheet.getFri_hours())+Integer.parseInt(savetimesheet.getMon_hours())+Integer.parseInt(savetimesheet.getSat_hours())+Integer.parseInt(savetimesheet.getThurs_hours())+Integer.parseInt(savetimesheet.getSun_hours())+Integer.parseInt(savetimesheet.getTue_hours())+Integer.parseInt(savetimesheet.getWed_hours());
       			savetimesheet.setTotalWeekHours(String.valueOf(cal));
       			savetimesheet.setStatus("pending");
       			EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
       			ClientDetailsEntity client = this.clientDetailsRepository.findByClientid(savetimesheet.getClient().getClientid());
       			ProjectDetailsEntity proj = this.pojectDetailsRepository.findByProjectId(savetimesheet.getProj().getProjectId());
       			TaskDetailsEntity task = this.taskDetailsRepository.getById(savetimesheet.getTask().getTaskid());
       			savetimesheet.setTask(task);
       			savetimesheet.setProj(proj);
       			savetimesheet.setClient(client);
       			savetimesheet.setEmp(emp1);
       			savetimesheet.setCreated_Date(LocalDateTime.now());
       			SaveTimeSheet  result=  this.hrmsEntityRepo.save(savetimesheet);
       			timeSheetResponseForMonthYearWeek.setMessage("successfully added data in data base");
       			timeSheetResponseForMonthYearWeek.setSaveTimeSheet(result);
       		 return timeSheetResponseForMonthYearWeek;
            	   }
            	  
            	   else {
            		
            		   timeSheetResponseForMonthYearWeek.setMessage("Today is holliday enjoy your day");
            		   return timeSheetResponseForMonthYearWeek;
            	   }   
           }
    	
	}
		return timeSheetResponseForMonthYearWeek;
          
		
	}




	public void deleteTimeSheet(int id) {
		this.hrmsEntityRepo.deleteById(id);
	}
	public SaveTimeSheet getTimeSheetDetail(int id) {
		return this.hrmsEntityRepo.findById(id).get();
	}

	public void updateTimeSheet(SaveTimeSheet savetimesheet, int id) {
		int cal=Integer.parseInt(savetimesheet.getFri_hours())+Integer.parseInt(savetimesheet.getMon_hours())+Integer.parseInt(savetimesheet.getSat_hours())+Integer.parseInt(savetimesheet.getThurs_hours())+Integer.parseInt(savetimesheet.getSun_hours())+Integer.parseInt(savetimesheet.getTue_hours())+Integer.parseInt(savetimesheet.getWed_hours());
		savetimesheet.setTotalWeekHours(String.valueOf(cal));
		savetimesheet.setStatus("pending");
		EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
		ClientDetailsEntity client = this.clientDetailsRepository.findByClientid(savetimesheet.getClient().getClientid());
		ProjectDetailsEntity proj = this.pojectDetailsRepository.findByProjectId(savetimesheet.getProj().getProjectId());
		TaskDetailsEntity task = this.taskDetailsRepository.getById(savetimesheet.getTask().getTaskid());
		savetimesheet.setTask(task);
		savetimesheet.setProj(proj);
		savetimesheet.setClient(client);
		savetimesheet.setEmp(emp1);
		savetimesheet.setModifiedDate(LocalDateTime.now());
		savetimesheet.setId(id);
		
		this.hrmsEntityRepo.save(savetimesheet);

	}







	public SaveTimeSheet getTimeSheetDetails(int month, int year, int calweek,int id) {

		return this.hrmsEntityRepo.GetAllDetails(month, year, calweek,id);

		//return k;
	}




	public List<Object[]> getTimeSheetDetailsByMonth(int month,int empid,int year) { 

		return this.hrmsEntityRepo.getDetailsByMon(month, empid, year);

	}




	//public TimeSheetApprovalStatus timeSheetApproval(int empid) {
	//	log.info("entered into timeSheetApproval Method of TimeSheetDetails BusinessClass..");
	//		String status1=hrmsEntityRepo.getStatus(empid);
	//		if(status1=="Approve") {
	//timeSheetApprovalStatus.setTimeSheetId(hrmsEntityRepo.getTimeSheetId(empid));
	//timeSheetApprovalStatus.setApproverName("Nilesh");
	//		}else {
	//			System.out.println("wrong id");
	//		}
	//return timeSheetApprovalStatus;
	//	
	//}
	
	
	//public List<SaveTimeSheet> getEmpByReportingId(int repId) {
	//	
	//	return this.hrmsEntityRepo.getDetailByRepId(repId);
	//}
}



