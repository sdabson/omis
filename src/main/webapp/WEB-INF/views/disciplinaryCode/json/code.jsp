<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach var="code" items="${codes}" varStatus="status">
	{
		"label": "<c:out value='${code.value}'/> - <c:out value='${code.description}'/>",
		"value": "${code.id}"
	}
	<c:if test="${not status.last}">,</c:if>
</c:forEach>
]