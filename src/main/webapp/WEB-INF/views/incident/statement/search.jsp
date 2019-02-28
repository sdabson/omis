<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Search view for Incident statements.
 -
 - Author: Joel Norris
 - Version: 0.1.0 (February 7, 2019)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.incident.msgs.incident">
<head>
	<title><fmt:message key="incidentStatementLabel"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/incident/style/incident.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/incident/statement/scripts/searchIncidentStatement.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="includes/searchForm.jsp"/>
	<h1><a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/incident/statement/incidentActionMenu.html"></a><span class="visibleLinkLabel"><fmt:message key="incidentStatementLabel"/></span></h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>