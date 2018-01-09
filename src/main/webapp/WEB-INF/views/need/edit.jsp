<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Joel Norris
 - Version: 0.1.0 (Jul 14, 2015)
 - Since: OMIS 3.0
 --%>
 <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.need.msgs.need">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty casePlanObjective}">
				<fmt:message key="editCasePlanObjectiveHeader"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createCasePlanObjectiveHeader"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/need/scripts/need.js?VERSION=1"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/need/scripts/jquery/jquery.omis.need.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/need/casePlanObjective/casePlanObjectiveActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty casePlanObjective}">
				<fmt:message key="editCasePlanObjectiveHeader"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createCasePlanObjectiveHeader"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>