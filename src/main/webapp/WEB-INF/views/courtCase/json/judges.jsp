<%--
 - Judges.
 -
 - Note - this view may eventually make its way into the judges module.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach var="judge" items="${judges}" varStatus="status">
	{
		"label": "<c:out value='${judge.name.lastName}'/>, <c:out value='${judge.name.firstName}'/>",
		"value": "${judge.id}"
	}
	<c:if test="${not status.last}">,</c:if>
</c:forEach>
]