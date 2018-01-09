<%--
 - Location select.
 -
 - Author: Stephen Abson
 - Author: Josh Divine
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<select id="location" name="location">
	<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
	<c:forEach var="location" items="${locations}">
		<c:choose>
			<c:when test="${not empty defaultLocation and defaultLocation eq location}">
				<option value="${location.id}" selected="selected"><c:out value="${location.organization.name}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${location.id}"><c:out value="${location.organization.name}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>