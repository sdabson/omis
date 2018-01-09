<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
<c:forEach var="organization" items="${organizations}" varStatus="status">
	"${organization.name}"
	<c:if test="${not status.last and (status.count ne status.end)}">,</c:if>
</c:forEach>
]