<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
[
<c:forEach var="answerValue" items="${answerValues}" varStatus="status">
	{  "label": "<c:out value="${answerValue.description}"/>",
	"value": "${answerValue.id}" }
	<c:if test="${not status.last and (status.count ne status.end)}">,</c:if>
</c:forEach>
]