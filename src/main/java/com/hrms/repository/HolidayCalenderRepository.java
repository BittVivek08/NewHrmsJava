package com.hrms.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hrms.entity.HolidayCalenderEntity;

public interface HolidayCalenderRepository extends JpaRepository<HolidayCalenderEntity, Integer> {


	// getHloidayByName
	public HolidayCalenderEntity findByDate(LocalDate date);

	/*
	 * @Query("SELECT l.date FROM HolidayCalenderEntity l") public List<Date>
	 * findAllDate();
	 */

	@Query("SELECT l.date FROM HolidayCalenderEntity l")
	public List<LocalDate> finDates();

	List<HolidayCalenderEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

 
}
