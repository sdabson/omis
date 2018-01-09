<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.staff.msgs.staff">
<head>
	<title><fmt:message key="staffIndexTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
</head>
<body>
	<h1><fmt:message key="staffIndexTitle"/></h1>
		<ul id="taskLinks" class="links taskLinks">
			<li class="header"><h2><fmt:message key="staffTasksHeader"/></h2></li>
		<sec:authorize access="hasRole('STAFF_TITLE_CREATE') or hasRole('ADMIN')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/staff/title/create.html">
				<fmt:message key="createStaffTitleTitle"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('STAFF_TITLE_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/staff/title/list.html">
				<fmt:message key="listStaffTitlesLink"/></a></li>
		</sec:authorize>
	</ul>
</body>
</fmt:bundle>
</html>