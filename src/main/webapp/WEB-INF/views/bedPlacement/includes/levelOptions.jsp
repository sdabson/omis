<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="level" items="${matchingLevels}" varStatus="status">
		<c:set var="levelName" value="${level.name}"/>
		<c:if test="${not empty level.section}">
			<c:set var="levelName" value="${level.section.name} - ${levelName}"/>
		</c:if>
		<c:if test="${not empty level.unit}">
			<c:set var="levelName" value="${level.unit.name} - ${levelName}"/>
		</c:if>
		<c:if test="${not empty level.complex}">
			<c:set var="levelName" value="${level.complex.name} - ${levelName}"/>
		</c:if>
		<c:choose>
			<c:when test="${not empty bedPlacementForm.level and bedPlacementForm.level eq level }">
				<option value="${level.id}" selected="selected"><c:out value="${levelName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${level.id}"><c:out value="${levelName}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>