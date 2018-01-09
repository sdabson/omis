<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Biographic and Contact Section screen for PSI
 -
 - Author: Jonny Santy
 - Version: 0.1.0 (Nov 1, 2016)
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.presentenceinvestigation.msgs.biographicandcontactsection">
<head>
	<title>
 		<c:choose> 
 			<c:when test="${not empty caution}"><fmt:message key="editBiographicAndContactSectionHeader"/></c:when> 
			<c:otherwise><fmt:message key="createBiographicAndContactSectionHeader"/></c:otherwise> 
 		</c:choose> 
	</title>
	
	
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/courtCaseCondition/style/courtCaseCondition.css" /> --%>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeaderResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.tools.js?Version=1"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/scripts/searchUserAccount.js"> </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/biographicAndContactSection/scripts/JQuery/jquery.omis.presentenceinvestigation.js?Version=1"></script>
    
    
    
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/caution/scripts/caution.js?VERSION=1"> </script> --%>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1> 
		<c:choose>
			<c:when test="${not empty caution}"><fmt:message key="editBiographicAndContactSectionHeader"/></c:when>
			<c:otherwise><fmt:message key="createBiographicAndContactSectionHeader"/></c:otherwise>
		</c:choose>
		
	</h1>
    <jsp:include page="includes/editForm.jsp"/> 
</body>
</fmt:bundle>
</html>