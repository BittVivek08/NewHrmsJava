package com.hrms.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hrms.beans.JobHistoryResponse;
import com.hrms.entity.EmployeeJobHistory;
import com.hrms.repository.EmployeePersonalInfoRepository;
import com.hrms.service.EmployeePersonalInfoService;

@Service
public class EmployeePersonalInfoServiceImpl implements EmployeePersonalInfoService {

//	private static Logger logger = Logger.class;

	@Autowired
	private EmployeePersonalInfoRepository employeePersonalInfoRepository;

	@Autowired
	private JobHistoryResponse historyResponse;

	@Override
	public JobHistoryResponse saveJobHistory(EmployeeJobHistory employeeJobHistory) {
//		logger.info("Entered into saveJobHistory()");

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

		EmployeeJobHistory joblist = employeePersonalInfoRepository.getByPositionId(positionId);
		return joblist;
	}

	@Override
	public List<EmployeeJobHistory> getAllJobHistory() {

		return employeePersonalInfoRepository.findAll();
	}

	@Override
	public JobHistoryResponse deletePositionId(int positionId) {
		
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

		EmployeeJobHistory empjh = employeePersonalInfoRepository.getByPositionId(positionId);
		try {
			if (empjh != null) {
				
//				empjh.setPositionId(employeeJobHistory.getPositionId());
				empjh.setPositionName(employeeJobHistory.getPositionName());
				empjh.setDepartmentName(employeeJobHistory.getDepartmentName());
				empjh.setJobTitleId(employeeJobHistory.getJobTitleId());
				empjh.setJobTitleName(employeeJobHistory.getJobTitleName());
				empjh.setClientName(employeeJobHistory.getClientName());
				empjh.setVendorName(employeeJobHistory.getVendorName());
				empjh.setAmountPaid(employeeJobHistory.getAmountPaid());
				empjh.setFromDate(employeeJobHistory.getFromDate());
				empjh.setUserId(employeeJobHistory.getUserId());
				empjh.setAmountRecieved(employeeJobHistory.getAmountRecieved());
				empjh.setCreatedDate(employeeJobHistory.getCreatedDate());
				empjh.setCreatedBy(employeeJobHistory.getCreatedBy());
				empjh.setModifiedby(employeeJobHistory.getModifiedby());
				empjh.setModifiedDate(employeeJobHistory.getModifiedDate());
				empjh.setIsactive(employeeJobHistory.getIsactive());

				return employeePersonalInfoRepository.save(empjh);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
