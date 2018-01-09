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
	<title><fmt:message key="staffTitlesTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/staff/scripts/staffTitles.js"> </script>
</head>
<body>
	<h1><fmt:message key="staffTitlesTitle"/></h1>
	<ul class="toolbar">
		<sec:authorize access="hasRole('STAFF_TITLE_CREATE') or hasRole('ADMIN')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/staff/title/create.html">
				<fmt:message key="createStaffTitleTitle"/></a></li>
		</sec:authorize>
	</ul>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>