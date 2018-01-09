<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<option value="">...</option>
<c:forEach var="city" items="${cities}">
<c:choose>
	<c:when test="${not empty defaultCity and city eq defaultCity}">
		<option value="${city.id}" selected="selected"><c:out value="${city.name}"/></option>
	</c:when>
	<c:otherwise>
		<option value="${city.id}"><c:out value="${city.name}"/></option>
	</c:otherwise>
</c:choose>
</c:forEach>