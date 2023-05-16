package com.hrms.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import static java.time.DayOfWeek.*;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@Slf4j
public class CurrentWeekDates {
//	private final static Logger logger = Logger.getLogger(CurrentWeekDates.class);

	public short currentWeekNo() {
		log.info("currentWeekNo() method");
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return (short) calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public HashMap<String, String> getDateDetails(LocalDate todayDate) {
		log.info("getDateDetails() method");
		HashMap<String, String> DateHM = new HashMap<String, String>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate today = todayDate;
		today = today.plusDays(1);
		LocalDate monday = today.with(previousOrSame(MONDAY));
		LocalDate sunday = today.with(nextOrSame(SUNDAY));
		LocalDate tuesday = monday.plusDays(1);
		LocalDate wednesday = monday.plusDays(2);
		LocalDate thursday = monday.plusDays(3);
		LocalDate friday = monday.plusDays(4);
		LocalDate saturday = monday.plusDays(5);
		LocalDate nextweek = monday.plusWeeks(1);
		LocalDate nmonday = nextweek.with(previousOrSame(MONDAY));
		LocalDate nsunday = nextweek.with(nextOrSame(SUNDAY));
		LocalDate previousweek = monday.minusWeeks(1);
		LocalDate pmonday = previousweek.with(previousOrSame(MONDAY));
		LocalDate psunday = previousweek.with(nextOrSame(SUNDAY));
		DateHM.put("currentWeek", monday.format(format) + "-" + sunday.format(format));
		DateHM.put("previousWeek", pmonday.format(format) + "-" + psunday.format(format));
		DateHM.put("nextWeek", nmonday.format(format) + "-" + nsunday.format(format));
		DateHM.put("monday", monday.format(format));
		DateHM.put("tuesday", tuesday.format(format));
		DateHM.put("wednesday", wednesday.format(format));
		DateHM.put("thursday", thursday.format(format));
		DateHM.put("friday", friday.format(format));
		DateHM.put("saturday",saturday.format(format));
		DateHM.put("sunday",sunday.format(format));
		DateHM.put("year", String.valueOf(today.getYear()));
		DateHM.put("month", String.valueOf(today.getMonthValue()));
		
		return DateHM;
	}
}