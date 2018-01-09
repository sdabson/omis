<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.user.msgs.userGroup">
<c:forEach var="group" items="${groups}">
	<tr>
		<td>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_GROUP_VIEW')">
			<a class="viewEditLink" href="${pageContext.request.contextPath}/user/admin/userGroup/edit.html?group=${group.id}"><span class="linkLabel"><fmt:message key="editLink"/></span></a>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_GROUP_REMOVE')">
			<a class="removeLink" href="${pageContext.request.contextPath}/user/admin/userGroup/remove.html?group=${group.id}"><span class="linkLabel"><fmt:message key="removeLink"/></span></a>
		</sec:authorize>
		</td>
		<td>
			<c:out value="${group.name}"/>
		</td>
		<td>
			<c:out value="${group.description}"/>
		</td>
		<td>
			<c:forEach var="role" items="${group.roles}">
				[<c:out value="${role.name}"/>]
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>