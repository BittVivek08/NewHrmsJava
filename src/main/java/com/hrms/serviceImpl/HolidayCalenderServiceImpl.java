package com.hrms.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

		holiday.setGroupid(1);
		holiday.setCreatedby(1);
		holiday.setCreateddate(LocalDateTime.now());
		holiday.setModifiedby(1);
		holiday.setIsactive(1);
		holiday.setModifieddate(LocalDateTime.now());
		HolidayCalenderEntity holidaySave = holiRepo.save(holiday);
		if (holidaySave != null) {
			bean.setMsg("successfully Holiday Saved");
			bean.setStatus(true);
			logging.info("successfully Holiday saved in service ");
		} else {
			bean.setMsg("Holday successfully not saved");
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

		// this.logging.info("updateHoliday.getDate()" + updateHoliday.getDate());
		// this.logging.info("updateHoliday.getHolidayDescription()" +
		// updateHoliday.getHolidaydescription());
		// this.logging.info("updateHoliday.getHolidayName()" +
		// updateHoliday.getHolidayname());

		Optional<HolidayCalenderEntity> holiday = holiRepo.findById(id);
		HolidayCalenderEntity holidayDB = null;
		if (holiday.isPresent()) { //
			holidayDB = holiday.get();
			holidayDB.setDate(updateHoliday.getDate());
			holidayDB.setCreatedby(1);
			holidayDB.setGroupid(0);
			holidayDB.setCreateddate(LocalDateTime.now());
			holidayDB.setHolidaydescription(updateHoliday.getHolidaydescription());
			holidayDB.setHolidayname(updateHoliday.getHolidayname());
			holidayDB.setHolidayyear(updateHoliday.getHolidayyear());
			holidayDB.setIsactive(1);
			holidayDB.setModifiedby(1);
			holidayDB.setModifieddate(LocalDateTime.now());

			// holidayDB.setHolidayName(updateHoliday.getHolidayname());
			// holidayDB.setHolidayDescription(updateHoliday.getHolidaydescription());
		}

		return holiRepo.save(holidayDB);
	}

	// GetById
	@Override
	public HolidayCalenderEntity getHolidayById(int id) {

		this.logging.info("Entered Get holiday By Id in service");

		Optional<HolidayCalenderEntity> findById = this.holiRepo.findById(id);

		this.logging.info("Successfully get Holiday by id in service");

		return findById.get();
	}

	// deleteHolidayById
	@Override
	public EntityBeanResponseCommon deleteHoliday(int id) {

		this.logging.info("Entered Delete Holiday By id in service");

		this.holiRepo.deleteById(id);
		bean.setMsg("Successfully Deleted id :" + id);
		bean.setStatus(true);

		this.logging.info("Successfully Deleted holiday By id");

		return bean;
	}

	@Override
	public List<LocalDate> getalllocaldates() {
		List<LocalDate> finDates = this.holiRepo.finDates();
		return finDates;
	}

	// updateBybean
	@Override
	public EntityBeanResponseCommon updateHolidayById(int id, HolidayCalenderEntity update) {
		
		this.logging.info("Entered update Holiday method in service ");

		Optional<HolidayCalenderEntity> holiday = holiRepo.findById(id);
		HolidayCalenderEntity holidayDB = null;
		if (holiday.isPresent()) { //
			holidayDB = holiday.get();
			holidayDB.setDate(update.getDate());
			holidayDB.setCreatedby(1);
			holidayDB.setGroupid(0);
			holidayDB.setCreateddate(LocalDateTime.now());
			holidayDB.setHolidaydescription(update.getHolidaydescription());
			holidayDB.setHolidayname(update.getHolidayname());
			holidayDB.setHolidayyear(update.getHolidayyear());
			holidayDB.setIsactive(1);
			holidayDB.setModifiedby(1);
			holidayDB.setModifieddate(LocalDateTime.now());

			HolidayCalenderEntity save = this.holiRepo.save(holidayDB);
			this.logging.info("successfully updated holiday in sevice");
			if (save != null) {
				this.bean.setMsg("Successfully updated Holiday id " + id);
				this.bean.setStatus(true);
			} else {
				this.bean.setMsg("Failed to update ");
				this.bean.setStatus(false);
				this.logging.info("failed to update holiday in service ");
			}

		}

		return bean;
	}

	@Override
	public HolidayCalenderEntity getHolidayBydate(LocalDate holidayDate) {
		// TODO Auto-generated method stub
		return null;
	}


}
