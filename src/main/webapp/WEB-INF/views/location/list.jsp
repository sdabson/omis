<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.location.msgs.location">
<head>
	<title>
		<c:choose>
		<c:when test="${not empty organization}">
			<fmt:message key="locationListingByOrganizationTitle">
				<fmt:param>${organization.name}</fmt:param>
			</fmt:message>
		</c:when>
		<c:otherwise>
			<fmt:message key="locationListingTitle"/>
		</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listResources.jsp"/>
</head>
<body>
	<h1>
		<c:choose>
		<c:when test="${not empty organization}">
			<fmt:message key="locationListingByOrganizationTitle">
				<fmt:param>${organization.name}</fmt:param>
			</fmt:message>
		</c:when>
		<c:otherwise>
			<fmt:message key="locationListingTitle"/>
		</c:otherwise>
		</c:choose>
	</h1>
	<ul id="locationssToolbar" class="toolbar">
		<li><a class="createLink" href="${pageContext.request.contextPath}/location/create.html?organization=${organization.id}"><span class="visibleLinkLabel"><fmt:message key="createLink"/></span></a></li>
		<c:if test="${not empty organization}">
		<li><a class="listLink" href="${pageContext.request.contextPath}/location/list.html"><fmt:message key="showAllLocationsLink"/></a></li>
		</c:if>
	</ul>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>