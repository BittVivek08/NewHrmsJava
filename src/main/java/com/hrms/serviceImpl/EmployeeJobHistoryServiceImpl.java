package com.hrms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.JobHistoryResponse;
import com.hrms.entity.EmployeeJobHistory;
import com.hrms.repository.EmployeeJobHistoryRepository;
import com.hrms.service.EmployeeJobHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeJobHistoryServiceImpl implements EmployeeJobHistoryService {

//	private static Logger logger = Logger.class;

	@Autowired
	private EmployeeJobHistoryRepository employeePersonalInfoRepository;

	@Autowired
	private JobHistoryResponse historyResponse;

	@Override
	public JobHistoryResponse saveJobHistory(EmployeeJobHistory employeeJobHistory) {
//		logger.info("Entered into saveJobHistory()");
		log.info("save job history business logic method");
		EmployeeJobHistory ss = employeePersonalInfoRepository.save(employeeJobHistory);
		if (ss != null) {
			historyResponse.setMessage("Employee Job history add successfully");
			historyResponse.setStatus(true);
		} else {
			historyResponse.setMessage("Failed to save details ");
			historyResponse.setStatus(false);
		}
		return historyResponse;

	}

	@Override
	public EmployeeJobHistory getByPositionId(int positionId) {
		log.info("get by positionId job history business logic method");
		EmployeeJobHistory joblist = employeePersonalInfoRepository.getByPositionId(positionId);
		return joblist;
	}

	@Override
	public List<EmployeeJobHistory> getAllJobHistory() {
		log.info("get all job history business logic method");
		return employeePersonalInfoRepository.findAll();
	}

	@Override
	public JobHistoryResponse deletePositionId(int positionId) {
		log.info("delete by positionId job history business logic method");
//		EmployeeJobHistory employee = this.employeePersonalInfoRepository.findByPositionId(positionId);
		EmployeeJobHistory employee = employeePersonalInfoRepository.getByPositionId(positionId);
		if(employee!=null) {
			this.employeePersonalInfoRepository.delete(employee);
			historyResponse.setMessage("Employee Job history delete successfully");
			historyResponse.setStatus(true);
		} else {
			historyResponse.setMessage("Failed to Delete details ");
			historyResponse.setStatus(false);
		}
		return historyResponse;
	}

	@Override
	public EmployeeJobHistory updateJobHistory(int positionId, EmployeeJobHistory employeeJobHistory) {
		log.info("update by PositionId job history business logic method");
		EmployeeJobHistory empjh = employeePersonalInfoRepository.getByPositionId(positionId);
		try {
			if (empjh != null) {
				
				empjh.setPositionId(employeeJobHistory.getPositionId());
				empjh.setPositionName(employeeJobHistory.getPositionName());
				empjh.setDepartmentName(employeeJobHistory.getDepartmentName());
				empjh.setJobTitleId(employeeJobHistory.getJobTitleId());
				empjh.setJobTitleName(employeeJobHistory.getJobTitleName());
				empjh.setClientName(employeeJobHistory.getClientName());
				empjh.setVendorName(employeeJobHistory.getVendorName());
				empjh.setAmountPaid(employeeJobHistory.getAmountPaid());
				empjh.setFromDate(employeeJobHistory.getFromDate());
				empjh.setToDate(employeeJobHistory.getToDate());

				return employeePersonalInfoRepository.save(empjh);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
