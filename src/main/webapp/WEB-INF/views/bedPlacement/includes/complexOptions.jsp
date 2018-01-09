<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="complex" items="${matchingComplexes}" varStatus="status">
		<c:choose>
		<c:when test="${not empty bedPlacementForm.complex and bedPlacementForm.complex eq complex}">
			<option value="${complex.id}" selected="selected"><c:out value="${complex.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${complex.id}"><c:out value="${complex.name}"/></option>
		</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>