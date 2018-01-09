<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to edit location terms.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.locationterm.msgs.locationReasonTerm">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty locationReasonTerm}">
				<fmt:message key="editLocationReasonTermTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createLocationReasonTermTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/locationTerm/style/links.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/locationTerm/scripts/locationReasonTerm.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a href="${pageContext.request.contextPath}/locationTerm/locationReasonTerm/locationReasonTermActionMenu.html?offender=${offenderSummary.id}" class="actionMenuItem" id="actionMenuLink"></a>
		<c:choose>
			<c:when test="${not empty locationReasonTerm}">
				<fmt:message key="editLocationReasonTermTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createLocationReasonTermTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>