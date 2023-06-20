package com.hrms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.WorkFlow;
import com.hrms.repository.WorkFlowRepository;
import com.hrms.request.bean.WorkFlowUpdateReqBean;
import com.hrms.response.bean.leaveReuestUpdateResponseBean;
import com.hrms.service.WorkFlowService;





@Service
public class WorkFlowServiceImpl implements WorkFlowService{

	
	@Autowired
	private WorkFlowRepository workFlowRepo;
	
	
	@Override
	public List<WorkFlow> getAllRequests() {
		
		return workFlowRepo.findAll();
		
	}


	@Override
	public List<WorkFlow> getData(String  empid, String rmId, String status) {
		
		
	  if (empid !=null && rmId !=null && status !=null) {
			return workFlowRepo.findByEmpidAndApprovalManagerIdAndStatus(empid,rmId,status);

     }
	 else if (empid != null && rmId ==null && status ==null ) {
	            return workFlowRepo.findByEmpid(empid);
	            
	        }else if (empid==null  && rmId != null && status != null ) {
	            return workFlowRepo.findByApprovalManagerIdAndStatus(rmId, status);
	            
	        } else if (empid==null  && rmId != null && status != null) {
	            return workFlowRepo.findByStatus(status);
	            
	        }else if (empid !=null && rmId == null && status != null) {
				return workFlowRepo.findByEmpidAndStatus(empid ,status);
				
			} else {
	            return workFlowRepo.findAll();
	        }
		
	}

	@Override
	public leaveReuestUpdateResponseBean updateReqStatus(WorkFlowUpdateReqBean bean) {
		
		leaveReuestUpdateResponseBean rs = new leaveReuestUpdateResponseBean();
		
		WorkFlow wFlow=this.workFlowRepo.findByEmpidAndReqidAndFeatureAndApprovalManagerId(
				bean.getEmpid(),bean.getReqId(), bean.getFeature(), bean.getApprovalManagerId());
		
		if (wFlow==null) {
			rs.setMessage("not update");
			rs.setStatus(false);
		}
		
		else	if (wFlow.getStatus().equalsIgnoreCase("pending")) {
			rs.setMessage("updated");
			rs.setStatus(true);
		}
		
		else {
			rs.setMessage("else part");
			rs.setStatus(false);
		}
		return rs;
	}

	
}
