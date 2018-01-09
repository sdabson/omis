<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.victim.msgs.victim">
<head>
	<title><fmt:message key="victimOffenderSearchTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchCSS.jsp"/>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderSearchFieldsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/searchOffenders.js"> </script>
</head>
<body>
	<h1><fmt:message key="victimOffenderSearchTitle"/></h1>
	<jsp:include page="includes/searchForm.jsp"/>
	<c:if test="${not empty offenderSummaries}">
		<jsp:include page="includes/searchResults.jsp"/>
	</c:if>
</body>
</fmt:bundle>
</html>