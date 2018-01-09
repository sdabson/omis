<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jun 3, 2014)
   - Since OMIS 3.0--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.health.msgs.health">
<h1>
	<fmt:message key="irregularScheduleDaysLabel"/>
</h1>
<ul class="toolbar">
	<li>
		<a href="${pageContext.request.contextPath}/health/provider/schedule/internal/addIrregularScheduleDay.html?providerAssignment=${providerAssignment.id}" id="createIrregularScheduleDay" class="createLink">
			<span><fmt:message key="newIrregularScheduleDayLabel"/></span>
		</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/health/provider/schedule/internal/addIrregularScheduleDays.html?providerAssignment=${providerAssignment.id}" id="createIrregularScheduleDateRange" class="createLink">
			<span><fmt:message key="newIrregularScheduleDaysLabel"/></span>
		</a>
			<label for="multipleIrregularScheduleStart">
				<fmt:message key="newIrregularScheduleStartDate"></fmt:message>
			</label>
			<input id="multipleIrregularScheduleStart" class="date"/>
			<label for="multipleIrregularScheduleEnd" >
				<fmt:message key="newIrregularScheduleEndDate"></fmt:message>
			</label>
			<input id="multipleIrregularScheduleEnd" class="date"/>
	</li>
</ul>
<table class="irregularScheduleDayList editTable" id="irregularScheduleDayList">
	<thead>
		<tr><th>
		</th><th>
			<fmt:message key="dayLabel"/>
		</th><th>
			<fmt:message key="startTimeLabel"/>
		</th><th>
			<fmt:message key="endTimeLabel"/>
		</th></tr>
	</thead>
	<tbody>
		<c:set var="irregularScheduleDays" value="${providerSchedule.irregularScheduleDays}"  scope="request"/>
		<jsp:include page="irregularScheduleTableBodyRows.jsp"/>
	</tbody>
</table>
</fmt:bundle>