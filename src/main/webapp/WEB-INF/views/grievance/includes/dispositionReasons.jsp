<%--
  - Disposition reason options.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="dispositionReason" items="${dispositionReasons}">
	<c:choose>
		<c:when test="${not empty defaultDispositionReason and defaultDispositionReason eq dispositionReason}">
			<option value="${dispositionReason.id}" selected="selected"><c:out value="${dispositionReason.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${dispositionReason.id}"><c:out value="${dispositionReason.name}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>