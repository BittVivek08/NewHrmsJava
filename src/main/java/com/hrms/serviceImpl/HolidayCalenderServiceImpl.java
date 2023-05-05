package com.hrms.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.HolidayCalenderEntity;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.service.HolidayCalenderService;

@Service
public class HolidayCalenderServiceImpl implements HolidayCalenderService {

	Logger logging = LoggerFactory.getLogger(HolidayCalenderServiceImpl.class);

	@Autowired
	private HolidayCalenderRepository holiRepo;

	@Autowired
	private EntityBeanResponseCommon bean;

	@Override
	public EntityBeanResponseCommon saveHoliday(HolidayCalenderEntity holiday) {

		logging.info("Entered SaveholidayMethod in service class impl");

		HolidayCalenderEntity holidaySave = holiRepo.save(holiday);
		if (holidaySave != null) {
			bean.setMsg("successfully Holiday Saved");
			bean.setStatus(true);
			logging.info("successfully Holiday saved in service ");
		} else {
			bean.setMsg("Holday successfull not saved");
			bean.setStatus(false);
			logging.warn("Exception occured in service");
			
		}

		return bean;
	}

	@Override
	public List<HolidayCalenderEntity> getAllHolidays() {
		logging.info("Entered get all holidays method in servcie ");
		
		Iterable<HolidayCalenderEntity> findAllHolidays = holiRepo.findAll();
		
		logging.info("successfully fetched Holidays in service");
		
		return (List<HolidayCalenderEntity>) findAllHolidays;
	}

	@Override
	public HolidayCalenderEntity updateHoliday(int id, HolidayCalenderEntity updateHoliday) {
		
		this.logging.info("Entered update holiday by id method in service ");
		
		Optional<HolidayCalenderEntity> holiday = holiRepo.findById(id);
		
		holiday.orElseThrow().setDate(updateHoliday.getDate());
		holiday.orElseThrow().setHolidayName(updateHoliday.getHolidayName());
		holiday.orElseThrow().setHolidayDescription(updateHoliday.getHolidayDescription());
		
		//HolidayCalenderEntity updatedHloiday = holiRepo.save(holiday);
		return this.holiRepo.save(holiday.orElseThrow());
	}

	
	/*
	 * //GetbyDate
	 * 
	 * @Override public HolidayCalenderEntity getHolidayBydate(LocalDate date) {
	 * 
	 * this.logging.info("Entered Get Holiday By Date method in Service ");
	 * 
	 * HolidayCalenderEntity findByDate =
	 * this.holiRepo.finHolidayCalenderEntity(date);
	 * 
	 * this.logging.info("Successfully get holiday by date in service"); return
	 * findByDate; }
	 */

	
	
	//GetById
	@Override
	public HolidayCalenderEntity getHolidayById(int id) {
		
		this.logging.info("Entered Get holiday By Id in service");
		
		Optional<HolidayCalenderEntity> findById = this.holiRepo.findById(id);
		
		this.logging.info("Successfully get Holiday by id in service");
		
		return findById.orElseThrow();
	}
	
	
   //deleteHolidayById
	@Override
	public EntityBeanResponseCommon deleteHoliday(int id) {
		
		this.logging.info("Entered Delete Holiday By id in service");
		
		this.holiRepo.deleteById(id);
		bean.setMsg("Successfully Deleted id :"+id);
		bean.setStatus(true);
		
		this.logging.info("Successfully Deleted holiday By id");
		
		return bean;
	}


   
	@Override
	public List<LocalDate> getalllocaldates() {
		List<LocalDate> finDates = this.holiRepo.finDates();
		return finDates;
	}

	@Override
	public HolidayCalenderEntity getHolidayBydate(LocalDate holidayDate) {
		HolidayCalenderEntity finHolidayCalenderEntity = this.holiRepo.findByDate(holidayDate);
		return finHolidayCalenderEntity;
	}
	
	
	
	
	
	/*
	 * @Override public List<Date> getAllDates() {
	 * 
	 * List<Date> findAllDate = this.holiRepo.findAllDate();
	 * 
	 * return findAllDate; }
	 */
	
	
	

	/*
	 * @Override public HolidayCalenderEntity UpdateHoliday(HolidayCalenderEntity
	 * updateHoliday) {
	 * 
	 * 
	 * HolidayCalenderEntity update = this.holiRepo.save(updateHoliday);
	 * 
	 * return update;
	 * 
	 * 
	 * //HolidayCalenderEntity updateHolidaDate = holiRepo.save(updateHoliday);
	 * 
	 * //return updateHolidaDate; }
	 */
	
	
	
	//getHloidayByname
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
