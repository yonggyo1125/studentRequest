<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
	</head>
	<body>
			<div class='calendar_tab'>
				<a href='?year=${cal.prevYear}&month=${cal.prevMonth}'>이전달</a>
				${cal.year}년 ${cal.month}월
				<a href='?year=${cal.nextYear}&month=${cal.nextMonth}'>다음달</a>
			</div>
			<section id="calendar">
				<ul class='yoil'>
					<c:forEach var="yoil" items="${cal.yoils}">
						<li>${yoil}</li>
					</c:forEach>
				</ul>
				<!--// yoil -->
				<ul>
				<c:forEach var="day" items="${cal.days}">
					<li>${day}</li>
				</c:forEach>	
				</ul>
			</section>
			
	</body>
</html>