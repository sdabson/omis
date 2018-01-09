<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach var="userAccountSearchResult" items="${userAccountSearchResults}" varStatus="status">
	{  "label": "${userAccountSearchResult.lastName}, ${userAccountSearchResult.firstName} (${userAccountSearchResult.username})", "value": "${userAccountSearchResult.id}" }
	<c:if test="${not status.last and (status.count ne status.end)}">,</c:if>
</c:forEach>
]