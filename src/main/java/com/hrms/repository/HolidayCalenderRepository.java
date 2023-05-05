package com.hrms.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.hrms.entity.HolidayCalenderEntity;

public interface HolidayCalenderRepository extends JpaRepository<HolidayCalenderEntity, Integer> {

	// HolidayCalenderEntity save(HolidayCalenderEntity holiday);

	// getHloidayByName
	public HolidayCalenderEntity findByDate(LocalDate date);
	
	
	
	

	/*
	 * @Query("SELECT l.date FROM HolidayCalenderEntity l") public List<Date>
	 * findAllDate();
	 */

	@Query("SELECT l.date FROM HolidayCalenderEntity l")
	public List<LocalDate> finDates();

}
