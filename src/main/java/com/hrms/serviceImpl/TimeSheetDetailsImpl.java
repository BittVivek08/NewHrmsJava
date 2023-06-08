package com.hrms.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.CurrentWeekRequest;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.repository.ClientDetailsRepository;
import com.hrms.repository.EmpRoleRepo;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.repository.IRequestForLeaveRepository;
import com.hrms.repository.MyLeaveRequestRepository;
import com.hrms.repository.ProjectDetailsRepository;
import com.hrms.repository.SaveTimeSheetRepo;
import com.hrms.repository.TaskDeatailsRepository;
import com.hrms.response.bean.TSResponseObj;
import com.hrms.response.bean.TimeSheetRequestBeanDate;
import com.hrms.response.bean.TimeSheetResponse;
import com.hrms.service.TimeSheetDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimeSheetDetailsImpl implements TimeSheetDetails {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SaveTimeSheetRepo saveTimeSheetRepo;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private ProjectDetailsRepository pojectDetailsRepository;
	@Autowired
	private TaskDeatailsRepository taskDetailsRepository;
	@Autowired
	private HolidayCalenderRepository holidayCalenderRepository;
	@Autowired
	private TimeSheetResponse timeSheetResponse;
	@Autowired
	private IRequestForLeaveRepository iRequestForLeaveRepository;
	@Autowired
	private TSResponseObj tsResp;
	@Autowired
	private MyLeaveRequestRepository myLeaveRequestRepository;
	@Autowired
	private EmpRoleRepo empRoleRepo1;

	public TimeSheetResponse saveTimeSheett(List<SaveTimeSheet> savetimesheetList) {
		log.info("Entered into saveTimeSheet  method of HrmsEmpTimeSheetService class");
		for (SaveTimeSheet savetimesheet : savetimesheetList) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
			ClientDetailsEntity client = this.clientDetailsRepository
					.findById(savetimesheet.getClient().getId()).get();
			ProjectDetailsEntity proj = this.pojectDetailsRepository
					.findByProjectId(savetimesheet.getProj().getProjectId());
			TaskDetailsEntity task = this.taskDetailsRepository.findById(savetimesheet.getTask().getTaskid())
					.get();
			savetimesheet.setTask(task);
			savetimesheet.setProj(proj);
			savetimesheet.setClient(client);
			savetimesheet.setEmp(emp1);
			savetimesheet.setStatus("pending");	
			savetimesheet.setCreatedBy(savetimesheet.getCreatedBy());
			savetimesheet.setWorkHours(savetimesheet.getWorkHours());
			savetimesheet.setCreatedDate(new Date());
			savetimesheet.setWorkDate(savetimesheet.getWorkDate());
			this.saveTimeSheetRepo.save(savetimesheet);
			timeSheetResponse.setMsg("timesheet detail save successfully");
			timeSheetResponse.setStatus(true);
		}
		return timeSheetResponse ;
	}

	public List<SaveTimeSheet> getTimeSheett(String empId) {

		return this.saveTimeSheetRepo.findByEmpId(empId);
	}

	public TimeSheetResponse UpdateTimeSheett(SaveTimeSheet savetimesheet,int id) {			
		EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
		ClientDetailsEntity client = this.clientDetailsRepository
				.findById(savetimesheet.getClient().getId()).get();
		ProjectDetailsEntity proj = this.pojectDetailsRepository
				.findByProjectId(savetimesheet.getProj().getProjectId());
		TaskDetailsEntity task = this.taskDetailsRepository.findById(savetimesheet.getTask().getTaskid())
				.get();
		savetimesheet.setTask(task);
		savetimesheet.setProj(proj);
		savetimesheet.setClient(client);
		savetimesheet.setEmp(emp1);
		savetimesheet.setStatus("pending");	
		savetimesheet.setCreatedBy(savetimesheet.getCreatedBy());
		savetimesheet.setWorkHours(savetimesheet.getWorkHours());
		savetimesheet.setCreatedDate(new Date());
		savetimesheet.setWorkDate(savetimesheet.getWorkDate());
		savetimesheet.setId(id);
		this.saveTimeSheetRepo.save(savetimesheet);

		timeSheetResponse.setMsg("successfully updated data");
		timeSheetResponse.setStatus(true);


		return timeSheetResponse ;

	}

	public  List<SaveTimeSheet> getTimeSheetByDate(CurrentWeekRequest date) {
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getTdate());
			List<SaveTimeSheet> timesheet=saveTimeSheetRepo.getByDate(date1);
			return timesheet;
		}
		catch(Exception e) {
			return null;
		}
	}

	public List<SaveTimeSheet> getTimeSheetByStartDateEndDate(TimeSheetRequestBeanDate date) {
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getStartDate());
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getEndDate());	
			List<SaveTimeSheet> timesheet=saveTimeSheetRepo.getDetailsByStartDateEndDate(date1,date2);
		return timesheet;
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
		return null;
	}

}
