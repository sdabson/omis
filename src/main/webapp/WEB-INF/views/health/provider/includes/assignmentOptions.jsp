<%--
 - Author: Stephen Abson
 -
 - The variable defaultProviderAssignment, if set, is used to determine which
 - option should initially be displayed as selected.
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
	<c:forEach var="providerAssignment" items="${providerAssignments}">
		<c:choose>
			<c:when test="${not empty defaultProviderAssignment and providerAssignment eq defaultProviderAssignment}">
				<option value="${providerAssignment.id}" selected="selected">
					<c:out value="${providerAssignment.provider.name.lastName}"/>,
					<c:out value="${providerAssignment.provider.name.firstName}"/>
					<c:out value="${providerAssignment.title.abbreviation}"/>
				</option>
			</c:when>
			<c:otherwise>
				<option value="${providerAssignment.id}">
					<c:out value="${providerAssignment.provider.name.lastName}"/>,
					<c:out value="${providerAssignment.provider.name.firstName}"/>
					<c:out value="${providerAssignment.title.abbreviation}"/>
				</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>