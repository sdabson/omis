<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.msgs.sessionManager">
<head>
	<title><fmt:message key="sessionManagerTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listResources.jsp"/>
</head>
<body>
	<h1><fmt:message key="sessionManagerTitle"/></h1>
	<table class="listTable">
		<thead>
			<tr>
			<th colspan="2"/>
			<th><fmt:message key="usernameHeader"/></th>
			<th><fmt:message key="authoritiesHeader"/></th>
			<th><fmt:message key="lastRequestHeader"/></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="session" items="${allSessionsOfAllPrincipals}">
			<tr>
			<td>
			<c:choose>
			<c:when test="${not session.expired}">
			<a href="${pageContext.request.contextPath}/sessionManager/expire.html?sessionId=${fn:replace(session.sessionId, '+', '%2B')}"><fmt:message key="expireLabel"/></a>
			</c:when>
			<c:otherwise>
			<fmt:message key="expired"/>
			</c:otherwise>
			</c:choose>
			</td>
			<td>
			<c:choose>
			<c:when test="${not session.expired}">
			<a href="${pageContext.request.contextPath}/sessionManager/expireAndDisable.html?sessionId=${fn:replace(session.sessionId, '+', '%2B')}&username=${session.principal.username}"><fmt:message key="expireAndDisableLabel"/></a>
			</c:when>
			<c:otherwise>
			<fmt:message key="expired"/>
			</c:otherwise>
			</c:choose>
			</td>
			<td><c:out value="${session.principal.username}"/></td>
			<td>
				<c:forEach var="authority" items="${session.principal.authorities}">
					[<c:out value="${authority}" />]
				</c:forEach>
			</td>
			<td><c:out value="${session.lastRequest}"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</fmt:bundle>
</html>