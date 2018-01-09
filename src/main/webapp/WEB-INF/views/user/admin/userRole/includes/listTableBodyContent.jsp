<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.user.msgs.userRole">
<c:forEach var="role" items="${roles}">
	<tr>
		<td>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ROLE_VIEW')">
			<a class="viewEditLink" href="${pageContext.request.contextPath}/user/admin/userRole/edit.html?role=${role.id}"><span class="linkLabel"><fmt:message key="editLink"/></span></a>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ROLE_REMOVE')">
			<a class="removeLink" href="${pageContext.request.contextPath}/user/admin/userRole/remove.html?role=${role.id}"><span class="linkLabel"><fmt:message key="removeLink"/></span></a>
		</sec:authorize>
		</td>
		<td>
			<c:out value="${role.name}"/>
		</td>
		<td>
			<c:out value="${role.description}"/>
		</td>
		<td>
			<c:forEach var="group" items="${role.groups}" varStatus="status">
				<c:choose>
				<c:when test="${not status.last}">
					<c:out value="${group.name}"/>,
				</c:when>
				<c:otherwise>
					<c:out value="${group.name}"/>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>