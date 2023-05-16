package com.hrms.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.hrms.beans.DateResponseInTimeSheet;

public class Timesheet {
	public DateResponseInTimeSheet getDateByYearAndCalWeek(int year, int calWeek) {
			DateResponseInTimeSheet dateRes = new DateResponseInTimeSheet();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setWeekDate(year, calWeek, Calendar.DAY_OF_MONTH);
			Date yourDate = cal.getTime();
			cal.setTime(yourDate);
			Date start, end;
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			start = cal.getTime();
			cal.add(Calendar.DATE, 6);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			end = cal.getTime();
			dateRes.setFromDate(formatter.format(start));
			dateRes.setTodate(formatter.format(end));
			return dateRes;
		}
}

