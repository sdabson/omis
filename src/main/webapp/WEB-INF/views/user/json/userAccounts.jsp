<%--
 - User accounts as JSON objects. 
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
[
<c:forEach var="userAccount" items="${userAccounts}" varStatus="status">
	{  "label": "<c:out value="${userAccount.user.name.lastName}"/>, <c:out value="${userAccount.user.name.firstName}"/><c:if test="${not empty userAccount.user.name.middleName}"> <c:out value="${fn:substring(userAccount.user.name.middleName, 0, 1)}"/>.</c:if><c:if test="${not empty userAccount.user.name.suffix}"> <c:out value="${userAccount.user.name.suffix}"/></c:if> (<c:out value="${userAccount.username}"/>)", "value": "${userAccount.id}" }
	<c:if test="${not status.last and (status.count ne status.end)}">,</c:if>
</c:forEach>
]