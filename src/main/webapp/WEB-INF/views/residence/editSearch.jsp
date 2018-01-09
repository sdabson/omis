<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.residence.msgs.residence" var="residenceBundle"/>
<head>
	<title>
		<fmt:message key="residenceSearchTitle" bundle="${residenceBundle}"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/home.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/residence/scripts/JQuery/jquery.omis.residenceSearch.js?VERSION=1"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/residence/scripts/residenceSearch.js?VERSION=1"></script>
</head>
<body>
	<sec:authorize access="hasRole('ADMIN') or hasRole('USER') or hasRole('APP_DEV')">
		<h1>
			<a class="actionMenuItem" id="searchCriteriaActionMenu" href="${pageContext.request.contextPath}/residence/searchCriteriaActionMenu.html"></a>
			<fmt:message key="residenceSearchTitle" bundle="${residenceBundle}"/>
		</h1>
	</sec:authorize>
		<jsp:include page="includes/editSearchForm.jsp"/>
</body>
</html>