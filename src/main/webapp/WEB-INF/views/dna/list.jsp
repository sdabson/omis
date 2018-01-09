<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.dna.msgs.dna">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/dna/scripts/dnaSamples.js"> </script>
 	<title>
		<fmt:message key="dnaListingHeader"/> 
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/dna/dnaSamplesActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="dnaListingHeader"/>	
	</h1>
 	<jsp:include page="includes/listTable.jsp"/> 
</body>
</fmt:bundle>
</html>