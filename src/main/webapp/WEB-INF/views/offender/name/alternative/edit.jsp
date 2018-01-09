<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Dec 9, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offender.msgs.alternativeOffenderName">
<head>
	<c:choose>
		<c:when test="${not empty alternativeNameAssociation}">
			<title>
				<fmt:message key="editAlternativeOffenderNameTitle"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</title>
		</c:when>
		<c:otherwise>
			<title>
				<fmt:message key="createAlterntaiveOffenderNameTitle"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</title>
		</c:otherwise>
	</c:choose>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offender/scripts/alternativeOffenderName.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offender/name/alternative/alternativeNameActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty alternativeNameAssociation}">
				<fmt:message key="editAlternativeOffenderNameTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createAlterntaiveOffenderNameTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>