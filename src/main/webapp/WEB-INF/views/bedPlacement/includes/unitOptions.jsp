<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="unit" items="${matchingUnits}" varStatus="status">
		<c:set var="unitName" value="${unit.name}"/>
		<c:if test="${not empty level.complex}">
			<c:set var="levelName" value="${unit.complex.name} - ${unitName}"/>
		</c:if>
		<c:choose>
			<c:when test="${not empty bedPlacementForm.unit and bedPlacementForm.unit eq unit }">
				<option value="${unit.id}" selected="selected"><c:out value="${unitName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${unit.id}"><c:out value="${unitName}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>