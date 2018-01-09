<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.user.msgs.userSearch" var="userSearchBundle"/>
<table class="searchResults">
	<thead>
		<tr>
			<th><fmt:message key="usernameLabel" bundle="${userSearchBundle}"/></th>
			<th><fmt:message key="userNameLabel" bundle="${userSearchBundle}"/></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="userSummary" items="${userSummaries}">
		<tr>
			<td>
				<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ACCOUNT_VIEW')">
					<a class="userAccountLink" href="${pageContext.request.contextPath}/user/admin/userAccount/edit.html?account=${userSummary.accountId}" title="<c:out value='${userSummary.username}'/>"><c:out value="${userSummary.username}"/></a>
				</sec:authorize>
			</td>
			<td>
				<c:out value="${userSummary.lastName}"/>,
				<c:out value="${userSummary.firstName}"/>
				<c:if test="${not empty userSummary.middleName}">
					<c:out value="${fn:substring(userSummary.middleName, 0, 1)}"/>
				</c:if>
				<c:if test="${not empty userSummary.suffix}">
					<c:out value="${userSummary.suffix}"/>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>