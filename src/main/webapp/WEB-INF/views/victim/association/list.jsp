<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.victim.msgs.victim">
<head>
	<title>
		<c:if test="${empty victim}">
			<fmt:message key="victimsTitle"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</c:if>
		<c:if test="${empty offender}">
			<fmt:message key="victimOffendersTitle"/>
		</c:if>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/victimAssociations.js"> </script>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<c:if test="${not empty victimSummary}">
		<jsp:include page="/WEB-INF/views/victim/includes/victimHeader.jsp"/>
	</c:if>
	<h1><a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/victim/association/associationsActionMenu.html?offender=${offenderSummary.id}&amp;victim=${victim.id}&amp;redirectTarget=${redirectTarget.name}"></a><c:if test="${empty victim}"><fmt:message key="victimsTitle"/></c:if><c:if test="${empty offender}"><fmt:message key="victimOffendersTitle"/></c:if></h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>