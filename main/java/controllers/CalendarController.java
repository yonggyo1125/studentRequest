package controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalendarController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요일 
		String[] yoils = {"월","화","수", "목", "금", "토", "일"};
		req.setAttribute("yoils", yoils);		
		
		String _year = req.getParameter("year");
		String _month = req.getParameter("month");
		int year = 0, month = 0;
		if (_year != null) {
			year = Integer.parseInt(_year);
		}
		
		if (_month != null) {
			month = Integer.parseInt(_month);
		}
		
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
				
		//int sDay = startDate.getDayOfMonth();
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
		
		req.setAttribute("days", days);
		/** 이달의 일 데이터 구하기 E */
		
		/** 현재 년, 월 S */
		req.setAttribute("year", year);
		req.setAttribute("month", _month);
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
		req.setAttribute("prevYear", prevYear);
		req.setAttribute("prevMonth", prevMonth);
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
		req.setAttribute("nextYear", nextYear);
		req.setAttribute("nextMonth", nextMonth);
		/** 이전 년,월 E */
		
		RequestDispatcher rd  = req.getRequestDispatcher("calendar.jsp");
		rd.forward(req, resp);
	}
}
