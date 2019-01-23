<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <fmt:bundle basename="omis.msgs.index">
  <head>
    <title><fmt:message key="homeTitle"/></title>
    	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/scrollContainerResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/eventRunnerResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/scrollContainerResources.jsp"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/home.css?VERSION=3"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/investigation/style/investigation.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/user/admin/style/userAdmin.css"/>
		<script src="${pageContext.request.contextPath}/resources/index/scripts/home.js?VERSION=1" type="text/javascript"></script>
  </head>
  <body>
	<jsp:include page="includes/homeLinks.jsp"/>
  </body>
  </fmt:bundle>
</html>