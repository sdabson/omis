<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title><fmt:message key="victimNotesTitle" bundle="${victimBundle}"/></title>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
		<script src="${pageContext.request.contextPath}/resources/victim/scripts/victimNotes.js?VERSION=1"> </script>
	</head>
	<body>
		<c:set value="${contactSummary}" var="contactSummary" scope="request"/>
		<jsp:include page="/WEB-INF/views/victim/includes/victimHeader.jsp"/>
		<h1><a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/victim/note/victimNotesActionMenu.html?victim=${victimSummary.id}"></a><fmt:message key="victimNotesTitle" bundle="${victimBundle}"/></h1>
		<jsp:include page="includes/listTable.jsp"/>
	</body>
</html>