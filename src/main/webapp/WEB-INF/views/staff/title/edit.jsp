<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.staff.msgs.staff">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty staffTitle}">
				<fmt:message key="editStaffTitleTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createStaffTitleTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/staff/scripts/staffTitle.js"> </script>
</head>
<body>
	<h1>
		<c:choose>
			<c:when test="${not empty staffTitle}">
				<fmt:message key="editStaffTitleTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createStaffTitleTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<ul class="toolbar">
		<sec:authorize access="hasRole('STAFF_TITLE_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/staff/title/list.html">
				<fmt:message key="listStaffTitlesLink"/></a></li>
		</sec:authorize>
	</ul>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>