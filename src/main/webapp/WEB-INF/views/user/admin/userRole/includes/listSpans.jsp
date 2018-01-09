<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="role" items="${roles}">
	<span class="userRole">
		<c:out value="${role.description}"/>
		<span class="userRoleName">
			<c:out value="${role.name}"/>
		</span>
	</span>
</c:forEach>