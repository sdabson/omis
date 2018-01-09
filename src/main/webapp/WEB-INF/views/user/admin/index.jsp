<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to manage users.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.user.msgs.adminIndex">
<head>
	<title><fmt:message key="userAdminTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
</head>
<body>
	<h1><fmt:message key="userAdminTitle"/></h1>
	<ul class="taskLinks">
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ACCOUNT_CREATE')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/user/admin/userAccount/create.html"><fmt:message key="createNewUserLink"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_SEARCH')">
   			<li><a class="searchLink" href="${pageContext.request.contextPath}/user/search.html"><fmt:message key="userSearchLink"/></a></li>
  		</sec:authorize>
	</ul>
</body>
</fmt:bundle>
</html>