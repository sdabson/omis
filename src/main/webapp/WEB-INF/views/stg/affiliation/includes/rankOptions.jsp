<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 07, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<option value="">...</option>
<c:forEach var="rank" items="${ranks}">
<c:choose>
	<c:when test="${not empty defaultRank and rank eq defaultRank}">
		<option value="${rank.id}" selected="selected"><c:out value="${rank.name}"/></option>
	</c:when>
	<c:otherwise>
		<option value="${rank.id}"><c:out value="${rank.name}"/></option>
	</c:otherwise>
</c:choose>
</c:forEach>