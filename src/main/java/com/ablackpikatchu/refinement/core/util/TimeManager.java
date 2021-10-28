package com.ablackpikatchu.refinement.core.util;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TimeManager {
	
	public static final ApriFools APRIL_FOOLS = new ApriFools();
	
	public static boolean isTimeOfYear(IYearTime time) {
		return time.isTime(LocalDate.now());
	}
	
	public static interface IYearTime {
		boolean isTime(LocalDate date);
	}
	
	private static class ApriFools implements IYearTime {

		@Override
		public boolean isTime(LocalDate date) {
			int day = date.get(ChronoField.DAY_OF_MONTH);
	        int month = date.get(ChronoField.MONTH_OF_YEAR);
	        return day == 1 && month == 4;
		}
		
	}

}
