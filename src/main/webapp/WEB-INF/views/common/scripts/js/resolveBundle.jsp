<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
<c:forEach var="message" items="${messages}" varStatus="status">
	'${message.key}': '<c:out value="${message.value}"/>'<c:if test="${not status.last}">,</c:if>
</c:forEach>
}