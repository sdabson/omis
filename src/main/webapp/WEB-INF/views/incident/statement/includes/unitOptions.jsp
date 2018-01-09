<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<jsp:include page="../../../includes/nullOption.jsp"/>
	<c:forEach var="unit" items="${units}" varStatus="status">
		<c:set var="unitName" value="${unit.name}"/>
		<c:if test="${not empty unit.complex}">
			<c:set var="levelName" value="${unit.complex.name} | ${unitName}"/>
		</c:if>
		<c:choose>
			<c:when test="${not empty incidentReportForm.unit and incidentReportForm.unit eq unit }">
				<option value="${unit.id}" selected="selected"><c:out value="${unitName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${unit.id}"><c:out value="${unitName}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>