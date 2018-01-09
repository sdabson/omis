<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.dna.msgs.dna">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/dna/scripts/dna.js"> </script>
    <title>
		<fmt:message key="dnaDetailHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	 <jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	 <h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/dna/dnaSampleActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="dnaDetailHeader"/> 
	 </h1>
	 <jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>