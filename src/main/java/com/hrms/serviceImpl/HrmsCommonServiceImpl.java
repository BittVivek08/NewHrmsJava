package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.CommonResponseBean;
import com.hrms.entity.EmpRole;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.GenderEntity;
import com.hrms.entity.JobTitlesEntity;
import com.hrms.entity.LanguageEntity;
import com.hrms.entity.MaritalStatusEntity;
import com.hrms.entity.NationalityEntity;
import com.hrms.entity.PositionEntity;
import com.hrms.entity.SalaryAccountClassTypeEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.repository.EmpRoleRepo;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.GenderRepository;
import com.hrms.repository.JobTitleRepository;
import com.hrms.repository.LanguageRepository;
import com.hrms.repository.MaritalStatusRepository;
import com.hrms.repository.NationalityRepository;
import com.hrms.repository.SalaryAccountClassTypeRepo;
import com.hrms.repository.SalaryCurrencyRepo;
import com.hrms.request.bean.JobTitleBean;
import com.hrms.request.bean.PersonalGenderBean;
import com.hrms.request.bean.PersonalMaritalStatusBean;
import com.hrms.request.bean.PersonalNationalityBean;
import com.hrms.request.bean.SalaryAccountClassTypeRequestBean;
import com.hrms.request.bean.SalaryCurrencyRequestBean;
import com.hrms.request.bean.personalLanguageBean;
import com.hrms.response.bean.ListOfPositionsResponseBean;
import com.hrms.service.HrmsCommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HrmsCommonServiceImpl implements HrmsCommonService {

	@Autowired
	private SalaryCurrencyRepo salCurrRepo;
	
	@Autowired
	private EmpRoleRepo empRoleRepo;
	
	@Autowired
	private SalaryAccountClassTypeRepo accTypeRepo;
	
	@Autowired
	private JobTitleRepository jobTitleRepo;
	
	@Autowired
	private GenderRepository genderRepo;
	
	@Autowired
	private MaritalStatusRepository statusRepo;
	
	@Autowired
	private NationalityRepository nationalityRepo;
	
	@Autowired
	private LanguageRepository languageRepo;
	
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private CommonResponseBean comResBean;

	@Override
	public CommonResponseBean saveSalaryCurrency(SalaryCurrencyRequestBean salaryCurrencyReqBean) {
		log.info("Entered into saving salaryCurrency  serviceImpl method");
		SalaryCurrencyEntity entity = new SalaryCurrencyEntity();
		
		try {
			
		entity.setCurrencyName(salaryCurrencyReqBean.getCurrencyName());
		entity.setCurrencyCode(salaryCurrencyReqBean.getCurrencyCode());
		entity.setDiscription(salaryCurrencyReqBean.getDiscription());
		entity.setIsActive(salaryCurrencyReqBean.getIsActive());
		entity.setCreatedDate(salaryCurrencyReqBean.getCreatedDate());
		entity.setModifiedDate(salaryCurrencyReqBean.getModifiedDate());
		
		SalaryCurrencyEntity saved = salCurrRepo.save(entity);

		if (saved != null) {
			comResBean.setMsg("Salry Currency Added Successfully ");
			comResBean.setStatus(true);
		}
		}catch (Exception e) {
			e.printStackTrace();
		
			comResBean.setMsg("Salary currency Addition Failed : " + e.getMessage() );
			comResBean.setStatus(false);
		}
		
		return comResBean;
		
	}

	@Override
	public List<SalaryCurrencyEntity> getAllSalaryCurrency() {
		log.info("Fething all salary currency");

		return salCurrRepo.findAll();
	}

	@Override
	public CommonResponseBean saveSalaryAccountType(SalaryAccountClassTypeRequestBean accountTypeReqBean) {
		log.info("Entered into Saving Salary AccountType method");
		SalaryAccountClassTypeEntity entity = new SalaryAccountClassTypeEntity();
		try {
			entity.setAccountClassType(accountTypeReqBean.getAccountClassType());
			entity.setDiscription(accountTypeReqBean.getDiscription());
			entity.setIsActive(accountTypeReqBean.getIsActive());
			entity.setCreatedDate(accountTypeReqBean.getCreatedDate());
			entity.setModifiedDate(accountTypeReqBean.getModifiedDate());
			
			SalaryAccountClassTypeEntity saved = accTypeRepo.save(entity);
			
			if(saved != null) {
				comResBean.setMsg("Account Type Saved Successfully");
				comResBean.setStatus(true);
			}
		}catch (Exception e){
			e.printStackTrace();
			comResBean.setMsg("Account Type Saving Failed : " + e.getMessage());
			comResBean.setStatus(false);
		}
		return comResBean;
	}

	@Override
	public List<SalaryAccountClassTypeEntity> getAllAccountType() {
		log.info("Fetching All Account Type");
		return accTypeRepo.findAll();
	}
	
	//Fetching EmpRole

	@Override
	public List<EmpRole> getAllEmpRole(){
		
		return empRoleRepo.findAll();

	}

	@Override
	public EmpRole getEmpRoleById(Integer id) {
		Optional<EmpRole> findById = empRoleRepo.findById(id);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	

	@Override
	public CommonResponseBean saveJobTitle(JobTitleBean bean) {
		
		log.info("save the jobtitle serviceimpl method");
		JobTitlesEntity entity = new JobTitlesEntity();
//		entity.setId(bean.getId());
		entity.setJobTitleName(bean.getJobTitleName());
		entity.setJobTitleCode(bean.getJobTitleCode());
		entity.setJobDescription(bean.getJobDescription());
		entity.setMinExpReq(bean.getMinExpReq());
		entity.setComments(bean.getComments());
		entity.setJobPayGradeCode(bean.getJobPayGradeCode());
		entity.setJobPayFrequency(bean.getJobPayFrequency());
		entity.setCreatedBy(bean.getCreatedOrModifiedBy());
		entity.setCreatedDate(bean.getCreatedDate());
		entity.setIsActive(bean.getIsActive());
//		if (bean.getId() > 0) {
		entity.setModifiedBy(bean.getCreatedOrModifiedBy());
		entity.setModifiedDate(bean.getModifiedDate());
//		}
		
		JobTitlesEntity saved = jobTitleRepo.save(entity);
		if (saved != null) {
			
			comResBean.setMsg("Job Title Saved Successfully............!");
			comResBean.setStatus(true);
		}else {
			
			comResBean.setMsg("Something went wrong............");
			comResBean.setStatus(false);
		}
		return comResBean;
	}

	@Override
	public Optional<JobTitlesEntity> getById(int id) {
		log.info("get by id jobtitle detail serviceimpl method");
		Optional<JobTitlesEntity> entity = jobTitleRepo.findById(id);
		return entity;
	}

	@Override
	public List<JobTitlesEntity> getAllDetails() {
		log.info("get the all jobtitle details serviceimpl method");
		List<JobTitlesEntity> AllDetails = jobTitleRepo.findAll();
		return AllDetails;
	}

	@Override
	public CommonResponseBean deleteById(int id) {
		log.info("delete by id jobtitle detail serviceimpl method");
		jobTitleRepo.deleteById(id);
		comResBean.setMsg("Job Title deleted Successfully.....!");
		comResBean.setStatus(true);
		return comResBean;
	}

	@Override
	public CommonResponseBean updateByid(int id,JobTitleBean bean ) {
		
		log.info("updated the jobtitle detail serviceimpl method");
		Optional<JobTitlesEntity> list = jobTitleRepo.findById(id);
		
		if(list.isPresent()) {
			
		JobTitlesEntity entity = list.get();
		
		entity.setJobTitleName(bean.getJobTitleName());
		entity.setJobTitleCode(bean.getJobTitleCode());
		entity.setJobDescription(bean.getJobDescription());
		entity.setMinExpReq(bean.getMinExpReq());
		entity.setComments(bean.getComments());
		entity.setJobPayGradeCode(bean.getJobPayGradeCode());
		entity.setJobPayFrequency(bean.getJobPayFrequency());
		entity.setCreatedBy(bean.getCreatedOrModifiedBy());
		entity.setCreatedDate(bean.getCreatedDate());
		entity.setIsActive(bean.getIsActive());
		entity.setModifiedBy(bean.getCreatedOrModifiedBy());
		entity.setModifiedDate(bean.getModifiedDate());
		
		JobTitlesEntity saved = jobTitleRepo.save(entity);
		
		if (saved != null) {
			
			comResBean.setMsg("Job Title updated Successfully..........!");
			comResBean.setStatus(true);
		}else {
			
			comResBean.setMsg("Job Title Not updated ..........!");
			comResBean.setStatus(false);
		}
		}
		return comResBean;
	}

	@Override
	public CommonResponseBean saveGenderDetails(PersonalGenderBean genderbean) {
		log.info("save the gender detail serviceimpl method");
		GenderEntity entity = new GenderEntity();
		entity.setGenderName(genderbean.getGenderName());
		entity.setGenderCode(genderbean.getGenderCode());
		entity.setCreatedDate(genderbean.getCreatedDate());
		entity.setModifiedDate(genderbean.getModifiedDate());
		entity.setIsActive(genderbean.getIsActive());
		GenderEntity save = genderRepo.save(entity);
		
		if(save != null) {
			
			comResBean.setMsg("gender details are saved successfully ........!");
			comResBean.setStatus(true);
		}else {
			comResBean.setMsg("gender details not saved ...........!");
			comResBean.setStatus(false);
		}
		return comResBean;
	}

	@Override
	public Optional<GenderEntity> getByGenderId(int id) {
		log.info("get by Id gender detail serviceimpl method");
		Optional<GenderEntity> getid = genderRepo.findById(id);
		return getid;
	}
	
	@Override
	public List<GenderEntity> getAllGenders() {
		 
		log.info("get the all gender details serviceimpl method");
		List<GenderEntity> AllDetails = genderRepo.findAll();
		return AllDetails;
	}
	
	@Override
	public CommonResponseBean deleteByGenderId(int id) {
		log.info("delete the gender detail serviceimpl method");
		 genderRepo.deleteById(id);
		 comResBean.setMsg("gender detail delete successfully........!");
		 comResBean.setStatus(true);
		 
		return comResBean;
	}

	@Override
	public CommonResponseBean updateByGenderId(int id, PersonalGenderBean genderBean) {
		log.info("updated the gender detail serviceimpl method");
		Optional<GenderEntity> entity=genderRepo.findById(id);
		if(entity.isPresent()) {
			
			GenderEntity entity1 = entity.get();
			entity1.setGenderName(genderBean.getGenderName());
			entity1.setGenderCode(genderBean.getGenderCode());
			entity1.setCreatedDate(genderBean.getCreatedDate());
			entity1.setModifiedDate(genderBean.getModifiedDate());
			entity1.setIsActive(genderBean.getIsActive());
			GenderEntity save = genderRepo.save(entity1);
			
			if(save != null) {
				
				comResBean.setMsg("gender details are updated successfully ........!");
				comResBean.setStatus(true);
			}else {
				comResBean.setMsg("gender details not updated ...........!");
				comResBean.setStatus(false);
			}
		}
		return comResBean;
	}

	@Override
	public CommonResponseBean saveMaritialDetails(PersonalMaritalStatusBean maritalbean) {
		
		log.info("save the maritalStatus detail serviceimpl method");
		MaritalStatusEntity entity = new MaritalStatusEntity();
		entity.setMaritalStatusName(maritalbean.getMaritalStatusName());
		entity.setMaritalCode(maritalbean.getMaritalCode());
		entity.setCreatedDate(maritalbean.getCreatedDate());
		entity.setModifiedDate(maritalbean.getModifiedDate());
		entity.setIsActive(maritalbean.getIsActive());
		MaritalStatusEntity save = statusRepo.save(entity);
		
		if(save != null) {
			
			comResBean.setMsg("marital status are saved successfully ........!");
			comResBean.setStatus(true);
		}else {
			comResBean.setMsg("marital status not saved ...........!");
			comResBean.setStatus(false);
		}
		return comResBean;
	}

	@Override
	public Optional<MaritalStatusEntity> getByMaritalId(int id) {
		log.info("get by id maritalStatus detail serviceimpl method");
		Optional<MaritalStatusEntity> getid = statusRepo.findById(id);
		return getid;
	}
	
	@Override
	public List<MaritalStatusEntity> getAllMaritalStatus() {
		
		log.info("get the all Marital details serviceimpl method");
		List<MaritalStatusEntity> AllDetails = statusRepo.findAll();
		return AllDetails;
	}

	@Override
	public CommonResponseBean deleteByMaritalId(int id) {
		log.info("delete the maritalStatus detail serviceimpl method");
		 statusRepo.deleteById(id);
		 comResBean.setMsg("marital Status delete successfully........!");
		 comResBean.setStatus(true);
		 
		return comResBean;
	}

	@Override
	public CommonResponseBean updateByMaritalId(int id, PersonalMaritalStatusBean maritalBean) {
		log.info("update the maritalStatus detail serviceimpl method");
		Optional<MaritalStatusEntity> entity=statusRepo.findById(id);
		if(entity.isPresent()) {
			
			MaritalStatusEntity entity1 = entity.get();
			
			entity1.setMaritalStatusName(maritalBean.getMaritalStatusName());
			entity1.setMaritalCode(maritalBean.getMaritalCode());
			entity1.setCreatedDate(maritalBean.getCreatedDate());
			entity1.setModifiedDate(maritalBean.getModifiedDate());
			entity1.setIsActive(maritalBean.getIsActive());
			MaritalStatusEntity save = statusRepo.save(entity1);
			
			if(save != null) {
				
				comResBean.setMsg("Marital status are updated successfully ........!");
				comResBean.setStatus(true);
			}else {
				comResBean.setMsg("Marital status not updated ...........!");
				comResBean.setStatus(false);
			}
		}
		return comResBean;
	}

	@Override
	public CommonResponseBean saveNationality(PersonalNationalityBean nationalityBean) {
		
		log.info("save the Natiobality detail serviceimpl method");
		NationalityEntity entity = new NationalityEntity();
		
		entity.setNationalityCode(nationalityBean.getNationalityCode());
		entity.setCreatedBy(nationalityBean.getCreatedBy());
		entity.setCreatedDate(nationalityBean.getCreatedDate());
		entity.setModifiedDate(nationalityBean.getModifiedDate());
		entity.setIsActive(nationalityBean.getIsActive());
		NationalityEntity save = nationalityRepo.save(entity);
		
		if(save != null) {
			
			comResBean.setMsg(" Nationality are saved successfully ........!");
			comResBean.setStatus(true);
		}else {
			comResBean.setMsg(" something went wrong...........!");
			comResBean.setStatus(false);
		}
		return comResBean;
	}

	@Override
	public Optional<NationalityEntity> getByNationalityId(int id) {
		Optional<NationalityEntity> nationality = nationalityRepo.findById(id);
		return nationality;
	}
	
	@Override
	public List<NationalityEntity> getAllNationality() {
		log.info("get the all Nationality details serviceimpl method");
		List<NationalityEntity> AllDetails = nationalityRepo.findAll();
		return AllDetails;
	}

	@Override
	public CommonResponseBean deleteByNatioalityId(int id) {
		nationalityRepo.deleteById(id);
		comResBean.setMsg("nationality delete successfully........!");
		comResBean.setStatus(true);
		return comResBean;
	}

	@Override
	public CommonResponseBean updateByNationaliyId(int id, PersonalNationalityBean nationalityBean) {
		log.info("update the Nationality serviceimpl method");
		Optional<NationalityEntity> entity=nationalityRepo.findById(id);
		if(entity.isPresent()) {
			
			NationalityEntity entity1 = entity.get();
			
			entity1.setNationalityCode(nationalityBean.getNationalityCode());
			entity1.setCreatedBy(nationalityBean.getCreatedBy());
			entity1.setCreatedDate(nationalityBean.getCreatedDate());
			entity1.setModifiedDate(nationalityBean.getModifiedDate());
			entity1.setIsActive(nationalityBean.getIsActive());
			NationalityEntity save = nationalityRepo.save(entity1);
			
			if(save != null) {
				
				comResBean.setMsg("Nationality are updated successfully ........!");
				comResBean.setStatus(true);
			}else {
				comResBean.setMsg("Nationality not updated ...........!");
				comResBean.setStatus(false);
			}
		}
		return comResBean;
	}

	@Override
	public CommonResponseBean saveLanguageDetails(personalLanguageBean bean) {
		
		log.info("save the Language details serviceimpl method");
		LanguageEntity entity = new LanguageEntity();
		entity.setLanguageName(bean.getLanguageName());
		entity.setDescription(bean.getDescription());
		entity.setCreatedDate(bean.getCreatedDate());
		entity.setModifiedDate(bean.getModifiedDate());
		entity.setIsActive(bean.getIsActive());
		
		LanguageEntity save = languageRepo.save(entity);
		
		if(save != null) {
			
			comResBean.setMsg("language deatils are saved successfully ........!");
			comResBean.setStatus(true);
		}else {
			comResBean.setMsg("details not saved ...........!");
			comResBean.setStatus(false);
		}
		return comResBean;

	}

	@Override
	public Optional<LanguageEntity> getBylanguageId(int id) {
		
		log.info("get by LanguageId serviceimpl method");
		Optional<LanguageEntity> findId = languageRepo.findById(id);
		return findId;
	}

	@Override
	public List<LanguageEntity> getAlllanguages() {
		
		log.info("get all Language details serviceimpl method");
		List<LanguageEntity> allLang = languageRepo.findAll();
		return allLang;
	}

	@Override
	public CommonResponseBean deleteBylanguageId(int id) {
		
		log.info("delete the Language details serviceimpl method");
		languageRepo.deleteById(id);
		comResBean.setMsg("Language is delete successfully........!");
		comResBean.setStatus(true);
		return comResBean;
	}

	@Override
	public CommonResponseBean updateBylanguageId(int id, personalLanguageBean bean) {
		
		log.info("update the Language details serviceimpl method");
		Optional<LanguageEntity> entity1=languageRepo.findById(id);
		
		if(entity1.isPresent()) {
		LanguageEntity entity = entity1.get();
		entity.setLanguageName(bean.getLanguageName());
		entity.setDescription(bean.getDescription());
		entity.setCreatedDate(bean.getCreatedDate());
		entity.setModifiedDate(bean.getModifiedDate());
		entity.setIsActive(bean.getIsActive());
		
		LanguageEntity save = languageRepo.save(entity);
		
		if(save != null) {
			
			comResBean.setMsg("language details are updated successfully ........!");
			comResBean.setStatus(true);
		}else {
			comResBean.setMsg("details not Updated ...........!");
			comResBean.setStatus(false);
		}
		}
		return comResBean;
	}

	@Override
	public CommonResponseBean fetchHRmanager(int businessunitId) {
		
		CommonResponseBean response = new CommonResponseBean();
		
		List<EmployeeDetails> hrSuperManagerList = empRepo.getHrManager(businessunitId);
		if (hrSuperManagerList.size() != 0) {
			response.setMsg("HR manager list  With management Retrived Successfully.");
			response.setStatus(true);
			response.setList(hrSuperManagerList);
		} else {
			response.setMsg("Please Select Other Role.");
			response.setStatus(true);
		}
		return response;
	}

	@Override
	public CommonResponseBean fetchIMMmanager(int businessunitId) {
		
        CommonResponseBean response = new CommonResponseBean();
		
		List<EmployeeDetails> hrSuperManagerList = empRepo.getImmManager(businessunitId);
		if (hrSuperManagerList.size() != 0) {
			response.setMsg("Imm Manager With Management Retrived Successfully.");
			response.setStatus(true);
			response.setList(hrSuperManagerList);
		} else {
			response.setMsg("Please Select Other Role.");
			response.setStatus(true);
		}
		return response;
	}

	@Override
	public CommonResponseBean fetchReportingManagerList(int empRoleId, int businessunitId, int departmentId) {
		CommonResponseBean response = new CommonResponseBean();
		List<EmployeeDetails> reportingManagerListAddEmployee =null;
		if(empRoleId <= 4) {
			reportingManagerListAddEmployee = empRepo.reportingManagerListAddEmployee(empRoleId, businessunitId, departmentId);
		
		}else if(empRoleId == 5){
			reportingManagerListAddEmployee  = empRepo.reportingManagerListAddEmployee2(empRoleId, businessunitId, departmentId);
		}
		
		if(reportingManagerListAddEmployee.size() != 0) {
			response.setMsg("Report Manager With Management Retrived Successfully.");
			response.setStatus(true);
			response.setList(reportingManagerListAddEmployee);
		}else {
			response.setMsg("Please Select Other Role.");
			response.setStatus(false);
		}
		return response;
	}

	@Override
	public ListOfPositionsResponseBean listOfPositions(int jobTitleId) {
		ListOfPositionsResponseBean response = new ListOfPositionsResponseBean();
		List<PositionEntity> listOfPositions = jobTitleRepo.listOfPositions(jobTitleId);
		
		if(!listOfPositions.isEmpty()) {
			response.setMsg("List Of Positions Retrived Successfully.");
			response.setStatus(true);
			response.setListOfPositions(listOfPositions);
		}else {
			response.setMsg("List Of Positions is empty.");
			response.setStatus(false);
		}
		return response;
	}

	@Override
	public CommonResponseBean fetchReporingListOnEmpRoleIdDu(int empRoleId, int departmentId) {
		CommonResponseBean response = new CommonResponseBean();
		List<EmployeeDetails> fetchReportManagetListBasedOnRoleIdDu = empRepo.fetchReportManagetListBasedOnRoleIdDu(empRoleId, departmentId);
		if(fetchReportManagetListBasedOnRoleIdDu.size() != 0) {
			response.setMsg("Report Manager With Management Retrived Successfully");
			response.setStatus(true);
			response.setList(fetchReportManagetListBasedOnRoleIdDu);
		}else {
			response.setMsg("Process of fetching Report Manager With Management Retrived Failed");
			response.setStatus(false);
		}
		return response;
	}

		
}
