<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="unit" items="${units}" varStatus="status">
		<c:choose>
			<c:when test="${not empty form.detainerWarrantProcessingStatus.unit and form.detainerWarrantProcessingStatus.unit eq unit }">
				<option value="${unit.id}" selected="selected"><c:out value="${unitName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${unit.id}"><c:out value="${unit.name}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>