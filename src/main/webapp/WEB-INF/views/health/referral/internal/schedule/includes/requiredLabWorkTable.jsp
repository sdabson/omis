<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
	<table id="labWorkTable" class="editTable">
		<thead>
			<tr>
				<th class="operations"/>
				<th><label><fmt:message key="labDateLabel"/></label></th>
				<th><label><fmt:message key="labWorkCategoryLabel"/></label></th>
				<th><label><fmt:message key="sampleLabLabel"/></label></th>
				<th><label><fmt:message key="resultsLabLabel"/></label></th>
				<th><label><fmt:message key="labWorkSchedulingNotesLabel"/></label></th>
			</tr>
		</thead>
		<tbody id="labWorkTableBody">
		<c:forEach var="lab" items="${labWorkItems}" varStatus="status">
			<c:if test="${lab.process}">
				<c:set var="labWorkIndex" value="${status.index}" scope="request"/>
				<jsp:include page="/WEB-INF/views/health/labWork/includes/labWorkAppointmentSchedulerTableRow.jsp" />
			</c:if>
		</c:forEach>
		</tbody>
	</table>
</fmt:bundle>