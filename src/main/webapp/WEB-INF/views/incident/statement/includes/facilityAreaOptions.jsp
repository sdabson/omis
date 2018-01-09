<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<jsp:include page="../../../includes/nullOption.jsp"/>
	<c:forEach var="facilityArea" items="${facilityAreas}" varStatus="status">
		<c:set var="facilityAreaName" value="${facilityArea.name}"/>
		<c:if test="${not empty facilityArea.complex}">
			<c:set var="levelName" value="${facilityArea.complex.name} | ${facilityAreaName}"/>
		</c:if>
		<c:choose>
			<c:when test="${not empty incidentReportForm.facilityArea and incidentReportForm.facilityArea eq facilityArea }">
				<option value="${facilityArea.id}" selected="selected"><c:out value="${facilityAreaName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${facilityArea.id}"><c:out value="${facilityAreaName}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>