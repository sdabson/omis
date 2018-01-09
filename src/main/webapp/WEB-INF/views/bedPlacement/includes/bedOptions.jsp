<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="bed" items="${matchingBeds}" varStatus="status">
			<c:choose>
				<c:when test="${not empty bedPlacementForm.bed and bedPlacementForm.bed eq bed}">
					<option value="${bed.id}" selected="selected"><c:out value="${bed.number}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${bed.id}"><c:out value="${bed.number}"/></option>
				</c:otherwise>
			</c:choose>
	</c:forEach>
	<c:forEach var="bed" items="${occupiedBeds}" varStatus="status">
		<c:choose>
			<c:when test="${not empty bedPlacementForm.bed and bedPlacementForm.bed eq bed}">
				<option class="unavailable" disabled="disabled" value="${bed.id}" selected="selected"><c:out value="${bed.number}"/></option>
			</c:when>
			<c:otherwise>
				<option class="unavailable" disabled="disabled"  value="${bed.id}"><c:out value="${bed.number}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>