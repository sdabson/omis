<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<fmt:bundle basename="omis.search.msgs.search">
<c:forEach var="user" items="${users}" varStatus="status">
	<tr>
		<td><a href="${pageContext.request.contextPath}/personSearch/user.json?user=${user.userId}">
			<fmt:message key="actionLabel"/>
			</a>
		</td>
		<td><c:out value="${user.lastName} ${user.middleName}, ${user.firstName}"/></td>
	</tr>
</c:forEach>
</fmt:bundle>