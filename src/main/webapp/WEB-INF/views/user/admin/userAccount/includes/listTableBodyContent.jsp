<%--
 - Table body of user accounts.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.user.msgs.userAccount">
<c:forEach var="account" items="${accounts}">
	<tr>
		<td>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ACCOUNT_VIEW')">
			<a class="viewEditLink" href="${pageContext.request.contextPath}/user/admin/userAccount/edit.html?account=${account.id}"><span class="linkLabel"><fmt:message key="editLink"/></span></a>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ACCOUNT_REMOVE')">
			<a class="removeLink" href="${pageContext.request.contextPath}/user/admin/userAccount/remove.html?account=${account.id}"><span class="linkLabel"><fmt:message key="removeLink"/></span></a>
		</sec:authorize>
		</td>
		<td>
			<c:out value="${account.username}"/>
		</td>
		<td>
			<c:out value="${account.user.name.lastName}, ${account.user.name.firstName}"/>
		</td>
		<%-- <td>
			<c:if test="${account.enabled}">
				<fmt:message key="yesLabel" bundle="${commonBundle}"/>
			</c:if>
		</td> --%>
	</tr>
</c:forEach>
</fmt:bundle>