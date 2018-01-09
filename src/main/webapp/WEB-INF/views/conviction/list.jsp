<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to list convictions.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.conviction.domain.conviction">
<head>
	<title><fmt:message key="convictionsTitle">
			<fmt:param value="${offenderNumber}"/>
		</fmt:message></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
</head>
<body>
	<h1><fmt:message key="convictionsTitle">
			<fmt:param value="${offenderNumber}"/>
		</fmt:message></h1>
		<ul id="cautionsToolbar" class="toolbar">
		<li><a class="createLink" href="${pageContext.request.contextPath}/conviction/create.html?offender=${offender.id}"><span class="linkLabel"><fmt:message key="createLink"/></span></a></li>
		<li><a class="printLink" href="#"><span class="linkLabel"><fmt:message key="printLabel"/></span></a></li>
		<li><a class="helpLink" href="#"><span class="linkLabel"><fmt:message key="helpLabel"/></span></a></li>
	</ul>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>