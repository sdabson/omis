<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach var="result" items="${results}" varStatus="status">
	{
		"label": "<c:out value='${result.firstName} ${result.lastName}'/>",
		"value": "${result.personId}"
	}
	<c:if test="${not status.last}">,</c:if>
</c:forEach>
]