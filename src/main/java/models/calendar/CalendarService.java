package models.calendar;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarService {
	Map<String, Object> data = new HashMap<>();
	public Map<String, Object> get(CalendarRequest request) {
		
		// 요일 
		String[] yoils = {"월","화","수", "목", "금", "토", "일"};
		data.put("yoils", yoils);
		
		int year = request.getYear();
		int month = request.getMonth();
		
		// 년, 월 값이 없는 경우 오늘 기준의 년, 월을 기본값으로 지정
		if (year <= 0 || month <= 0) {
			LocalDate today = LocalDate.now();
			year = today.getYear();
			month = today.getMonthValue();
		}
		
		// 이달의 시작일 
		LocalDate startDate = LocalDate.of(year, month, 1);
		
		// 이달의 마지막일
		LocalDate endDate = startDate.plusMonths(1).minusDays(1);
		
		int sDay = startDate.getDayOfMonth();
		int eDay = endDate.getDayOfMonth();
		int startPos = startDate.get(ChronoField.DAY_OF_WEEK) - 1;
		
		/** 이달의 일 데이터 구하기 S */
		// 1~9일 사이는 앞에 0을 붙인다.
		DecimalFormat df = new DecimalFormat("00");
		
		List<String> days = new ArrayList<>();
		for (int i = 1; i <= eDay + startPos ; i++) {
			String day = "";
			if (i > startPos) {
				day = df.format(i - startPos);
			} 
			
			days.add(day);
		}
		data.put("days", days);
		/** 이달의 일 데이터 구하기 E */
		
		/** 현재 년, 월 S */
		data.put("year", year);
		data.put("month", month);
		/** 현재 년, 월 E */
		
		/** 이전 년,월 S */
		int prevYear = year;
		int prevMonth = month;
		if (month == 1) {
			prevYear = year - 1;
			prevMonth = 12;
		} else {
			prevMonth--;
		}
		data.put("prevYear", prevYear);
		data.put("prevMonth", prevMonth);
		/** 이전 년,월 E */
		
		/** 다음 년,월 S */
		int nextYear = year;
		int nextMonth = month;
		if (month == 12) {
			nextYear++;
			nextMonth =1; 
		} else {
			nextMonth++;
		}
		data.put("nextYear", nextYear);
		data.put("nextMonth", nextMonth);
		/** 이전 년,월 E */
		
		return data;
	}
}
