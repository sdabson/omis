<%--
 - Location options.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/includes/nullOption.jsp"/>
	<c:forEach var="location" items="${locations}">
		<c:choose>
			<c:when test="${not empty defaultLocation and location eq defaultLocation}">
				<option value="${location.id}" selected="selected"><c:out value="${location.organization.name}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${location.id}"><c:out value="${location.organization.name}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>