<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.config.msgs.config">
<head>
	<title><fmt:message key="configurationSettingsTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
</head>
<body>
	<h1><fmt:message key="configurationSettingsTitle"/></h1>
	<p class="warning"><fmt:message key="configurationSettingsWarningMsg"/></p>
	<ul id="cautionsToolbar" class="toolbar">
		<li><a class="createLink" href="${pageContext.request.contextPath}/config/create.html"><span class="linkLabel"><fmt:message key="createConfigurationSettingLink"/></span></a></li>
		<li><a class="printLink" href="#"><span class="linkLabel"><fmt:message key="printLabel"/></span></a></li>
		<li><a class="helpLink" href="#"><span class="linkLabel"><fmt:message key="helpLabel"/></span></a></li>
	</ul>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>