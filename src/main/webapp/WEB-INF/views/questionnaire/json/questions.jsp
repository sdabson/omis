<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
[
<c:forEach var="question" items="${questions}" varStatus="status">
	{"label": "(<c:out value="${fn:toUpperCase(fn:substring(question.questionCategory, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(question.questionCategory, '_', ' '), 1, -1))}"/>) <c:out value="${question.text}"/>",
	"id": "${question.id}",
	"category": "${question.questionCategory}" }
	<c:if test="${not status.last and (status.count ne status.end)}">,</c:if>
</c:forEach>
]