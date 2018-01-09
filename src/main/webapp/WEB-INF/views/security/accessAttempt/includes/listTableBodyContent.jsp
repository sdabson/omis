<%--
 - Access attempt table.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:forEach var="accessAttempt" items="${accessAttempts}">
	<tr>
		<td><c:out value="${accessAttempt.username}"/></td>
		<td><fmt:formatDate pattern="MM/dd/yyyy h:mm:ss a" value="${accessAttempt.date}"/></td>
		<td><c:out value="${accessAttempt.remoteAddress}"/></td>
		<td><c:out value="${accessAttempt.remoteHost}"/></td>
		<td><c:out value="${accessAttempt.success}"/></td>
		<td><c:out value="${accessAttempt.userAgent}"/></td>
	</tr>
</c:forEach>