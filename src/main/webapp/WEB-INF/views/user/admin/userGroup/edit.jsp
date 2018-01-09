<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.user.msgs.userGroup">
<head>
	<title><fmt:message key="userGroupDetailTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/admin/style/userGroupDetail.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/admin/style/userAdmin.css"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
</head>
<body>
	<h1><fmt:message key="userGroupDetailTitle"/></h1>
	<ul class="toolbar">
		<li>
			<a class="userAdminLink" href="${pageContext.request.contextPath}/user/admin/index.html">
				<fmt:message key="userAdminIndexLink"/></a>
		</li>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_GROUP_LIST')">
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/user/admin/userGroup/list.html">
				<fmt:message key="listUserGroupsLink"/></a>
		</li>
		</sec:authorize>
	</ul>
	<jsp:include page="includes/editForm.jsp" />
</body>
</fmt:bundle>
</html>