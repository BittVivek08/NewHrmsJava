package com.hrms.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.Response;

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

import com.hrms.beans.CurrentWeekDatesResponse;
import com.hrms.beans.DateResponseInTimeSheet;
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
import com.hrms.responsebean.CommonTimeSheetbean;
import com.hrms.responsebean.DateResponseTimeSheet;
import com.hrms.responsebean.TimeSheetResponse;
import com.hrms.responsebean.TimeSheetResponseForMonthYearWeek;
import com.hrms.responsebean.TimeSheetsMonthlyBean;
import com.hrms.service.TimeSheetDetails;
import com.hrms.util.CurrentWeekDates;
import com.hrms.util.Timesheet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@ComponentScan({"com.hrms.repository.EmployeeRepository","com.hrms.entity.TimeSheetApprovalStatus"})
public class TimeSheetDetailsImpl implements TimeSheetDetails {

	@Autowired
	private EntityBeanResponse entityBeanResponse;

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

	public TimeSheetResponse saveTimeSheett(SaveTimeSheet savetimesheet) {
		log.info("entered into saveTimeSheett  method of HrmsEmpTimeSheetService class");
		LocalDate date1 = iRequestForLeaveRepository.findByStartDate(LocalDate.now(),
				savetimesheet.getEmp().getEmpId());
		LocalDate date = holidayCalenderRepository.findByDate1(LocalDate.now());
		String leave = iRequestForLeaveRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
		try {
			if (date == null && date1 == null && leave != savetimesheet.getEmp().getEmpId()) {

				int cal = Integer.parseInt(savetimesheet.getFri_hours())
						+ Integer.parseInt(savetimesheet.getMon_hours())
						+ Integer.parseInt(savetimesheet.getSat_hours())
						+ Integer.parseInt(savetimesheet.getThurs_hours())
						+ Integer.parseInt(savetimesheet.getSun_hours())
						+ Integer.parseInt(savetimesheet.getTue_hours())
						+ Integer.parseInt(savetimesheet.getWed_hours());
				savetimesheet.setTotalWeekHours(String.valueOf(cal));
				savetimesheet.setStatus("pending");
				savetimesheet.setIsActive(0<1);
				EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
				ClientDetailsEntity client = this.clientDetailsRepository
						.findById(savetimesheet.getClient().getId()).get();
				ProjectDetailsEntity proj = this.pojectDetailsRepository
						.findByProjectId(savetimesheet.getProj().getProjectId());
				TaskDetailsEntity task = this.taskDetailsRepository.getById(savetimesheet.getTask().getTaskid());
				savetimesheet.setTask(task);
				savetimesheet.setProj(proj);
				savetimesheet.setClient(client);
				savetimesheet.setEmp(emp1);
				savetimesheet.setCreatedBy(1);
				savetimesheet.setCreated_Date(LocalDateTime.now());
				SaveTimeSheet result = this.saveTimeSheetRepo.save(savetimesheet);
				timeSheetResponse.setMsg("successfully added data ");
				timeSheetResponse.setStatus(true);
				return timeSheetResponse;
			} else {
				timeSheetResponse.setMsg("today is leave/holiday you what to enter timesheetDetails than click yes/no");
				//

				if (date == null || date1 == LocalDate.now() || date == LocalDate.now()
						|| date1 == null && savetimesheet.getEmp().getEmpId() == leave) {

					if (savetimesheet.getRequest().equalsIgnoreCase("yes")) {

						int cal = Integer.parseInt(savetimesheet.getFri_hours())
								+ Integer.parseInt(savetimesheet.getMon_hours())
								+ Integer.parseInt(savetimesheet.getSat_hours())
								+ Integer.parseInt(savetimesheet.getThurs_hours())
								+ Integer.parseInt(savetimesheet.getSun_hours())
								+ Integer.parseInt(savetimesheet.getTue_hours())
								+ Integer.parseInt(savetimesheet.getWed_hours());
						savetimesheet.setTotalWeekHours(String.valueOf(cal));
						savetimesheet.setStatus("pending");
						EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
						ClientDetailsEntity client = this.clientDetailsRepository
								.findById(savetimesheet.getClient().getId()).get();
						ProjectDetailsEntity proj = this.pojectDetailsRepository
								.findByProjectId(savetimesheet.getProj().getProjectId());
						TaskDetailsEntity task = this.taskDetailsRepository
								.getById(savetimesheet.getTask().getTaskid());
						savetimesheet.setTask(task);
						savetimesheet.setProj(proj);
						savetimesheet.setClient(client);
						savetimesheet.setEmp(emp1);
						savetimesheet.setCreatedBy(1);
						savetimesheet.setCreated_Date(LocalDateTime.now());
						SaveTimeSheet result = this.saveTimeSheetRepo.save(savetimesheet);
						timeSheetResponse.setMsg("successfully added data in data base");
						timeSheetResponse.setStatus(true);
						return timeSheetResponse;
					}

					else {

						timeSheetResponse.setMsg("Today is holliday enjoy your day");
						return timeSheetResponse;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeSheetResponse;
	}

	public void deleteTimeSheet(int id) {
		this.saveTimeSheetRepo.deleteById(id);
	}

	public SaveTimeSheet getTimeSheetDetail(int id) {
		return this.saveTimeSheetRepo.findById(id).get();
	}

	public void updateTimeSheet(SaveTimeSheet savetimesheet, int id)

	{
		log.info("entered into updateTimeSheet  method of HrmsEmpTimeSheetService class");
		int cal = Integer.parseInt(savetimesheet.getFri_hours()) + Integer.parseInt(savetimesheet.getMon_hours())
		+ Integer.parseInt(savetimesheet.getSat_hours()) + Integer.parseInt(savetimesheet.getThurs_hours())
		+ Integer.parseInt(savetimesheet.getSun_hours()) + Integer.parseInt(savetimesheet.getTue_hours())
		+ Integer.parseInt(savetimesheet.getWed_hours());
		savetimesheet.setTotalWeekHours(String.valueOf(cal));
		savetimesheet.setStatus("pending");
		EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
		int creat = this.saveTimeSheetRepo.findByCretedBy(savetimesheet.getEmp().getEmpId(), id);
		int modified = this.saveTimeSheetRepo.findmodiBy(savetimesheet.getEmp().getEmpId(), id);
		ClientDetailsEntity client = this.clientDetailsRepository
				.findById(savetimesheet.getClient().getId()).get();
		ProjectDetailsEntity proj = this.pojectDetailsRepository
				.findByProjectId(savetimesheet.getProj().getProjectId());
		TaskDetailsEntity task = this.taskDetailsRepository.getById(savetimesheet.getTask().getTaskid());
		savetimesheet.setTask(task);
		savetimesheet.setProj(proj);
		savetimesheet.setClient(client);
		savetimesheet.setEmp(emp1);
		savetimesheet.setModifiedBy(creat + modified);
		savetimesheet.setModifiedDate(LocalDateTime.now());
		savetimesheet.setId(id);

		this.saveTimeSheetRepo.save(savetimesheet);

	}

	public SaveTimeSheet getTimeSheetDetails(int month, int year, int calweek, int id) {

		return this.saveTimeSheetRepo.GetAllDetails(month, year, calweek, id);

		// return k;
	}

	public List<Object[]> getTimeSheetDetailsByMonth(int month, String empid, int year) {

		return this.saveTimeSheetRepo.getDetailsByMon(month, empid, year);

	}

	public CurrentWeekDatesResponse getWeekDates(LocalDate date) {
		HashMap<?, ?> DateHM = new CurrentWeekDates().getDateDetails(date);
		CurrentWeekDatesResponse currentWeekDatesResponse = new CurrentWeekDatesResponse();
		currentWeekDatesResponse.setCurrentWeek(DateHM.get("currentWeek").toString());
		currentWeekDatesResponse.setNextWeek(DateHM.get("nextWeek").toString());
		currentWeekDatesResponse.setPreviousWeek(DateHM.get("previousWeek").toString());
		currentWeekDatesResponse.setMonDate((String) DateHM.get("monday"));
		currentWeekDatesResponse.setTueDate((String) DateHM.get("tuesday"));
		currentWeekDatesResponse.setWedDate((String) DateHM.get("wednesday"));
		currentWeekDatesResponse.setThuDate((String) DateHM.get("thursday"));
		currentWeekDatesResponse.setFriDate((String) DateHM.get("friday"));
		currentWeekDatesResponse.setSatDate((String) DateHM.get("saturday"));
		currentWeekDatesResponse.setSunDate((String) DateHM.get("sunday"));
		Calendar calendar = new GregorianCalendar();
		Date date1 = new Date();
		calendar.setTime(date1);
		currentWeekDatesResponse.setCalweek(calendar.get(Calendar.WEEK_OF_YEAR));
		currentWeekDatesResponse.setYear(Integer.parseInt((String) DateHM.get("year")));
		currentWeekDatesResponse.setMonth(Integer.parseInt((String) DateHM.get("month")));
		currentWeekDatesResponse.setMonth(Integer.parseInt((String) DateHM.get("month")));
		currentWeekDatesResponse.setMonthlyWeek(calendar.get(Calendar.WEEK_OF_MONTH));
		return currentWeekDatesResponse;
	}

	public DateResponseTimeSheet dateResponse(int year, int calWeek) {
		log.info("entered into dateResponse Method of TimeSheetDetails Service..");
		DateResponseInTimeSheet dateRes =new Timesheet().getDateByYearAndCalWeek(year, calWeek);
		DateResponseTimeSheet res = new DateResponseTimeSheet();
		if (dateRes != null) {
			res.setStatus(true);
			res.setMessage("Date Retrived SuccessFul");
			res.setDateResp(dateRes);
		} else {
			res.setStatus(false);
			res.setMessage("Date Retrived UnSuccessFul");
		}
		return res;

	}


	public void getEmployeesWhoDidNotAccessTimeSheet(int year, int month) {
		CommonTimeSheetbean response=new CommonTimeSheetbean();
		List<TimeSheetsMonthlyBean> beanList = new ArrayList<>();
		List<String> objList=null;
		List<String> objList1=null;
		if (year != 0 && month != 0) {
			if(saveTimeSheetRepo.getSaveTimeSheetempid(year, month)!= employeeRepository.findByEmpId("RST101")) {
				objList =saveTimeSheetRepo.getSaveTimeSheetempid(year, month) ;
			}
		}
		if (year != 0 && month == 0) {
			if(saveTimeSheetRepo.getSaveTimeSheetempid(year, month)!= employeeRepository.findByEmpId("RST101")) {
				objList1 =saveTimeSheetRepo.getSaveTimeSheetempid(year, month);
			}
			if ( objList !=null  && objList1 != null) {
				for (String obj : objList) {
					TimeSheetsMonthlyBean timeSheet = new TimeSheetsMonthlyBean();
					timeSheet.setEmployeeId(saveTimeSheetRepo.getTimeSheetempid(year, month));
					timeSheet.setEmployeeName("Nilesh");
					timeSheet.setMonTotalHours(0);
					timeSheet.setTueTotalHours(0);
					timeSheet.setWedTotalHours(0);
					timeSheet.setThursTotalHours(0);
					timeSheet.setFriTotalHours(0);
					timeSheet.setSatTotalHours(0);
					timeSheet.setSunTotalHours(0);
					timeSheet.setMonth(month);
					timeSheet.setYear(year);
					int repManId = employeeRepository.getByRepId(Integer.parseInt(obj));
					timeSheet.setReportingManagerName(employeeRepository.empName(repManId));
					beanList.add(timeSheet);
				}

			}
		}

	}

	//	public List<SaveTimeSheet> getWeekMonthNameByMonthId(int month) {
	//		List<SaveTimeSheet> listOfWeeks = saveTimeSheetRepo.getWeekMonthNameByMonthId(month);
	//		
	//		return listOfWeeks;
	//		
	//		
	//	}


	// public TimeSheetApprovalStatus timeSheetApproval(int empid) {
	// log.info("entered into timeSheetApproval Method of TimeSheetDetails
	// BusinessClass..");
	// String status1=hrmsEntityRepo.getStatus(empid);
	// if(status1=="Approve") {
	// timeSheetApprovalStatus.setTimeSheetId(hrmsEntityRepo.getTimeSheetId(empid));
	// timeSheetApprovalStatus.setApproverName("Nilesh");
	// }else {
	// System.out.println("wrong id");
	// }
	// return timeSheetApprovalStatus;
	//
	// }

	public List<EmployeeDetails> getEmpByReportingId(int repId) {

		return this.employeeRepository.getDetailByRepId(repId);
	}
}
