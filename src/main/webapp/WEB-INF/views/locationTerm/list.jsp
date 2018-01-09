<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to list location terms.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.locationterm.msgs.locationTerm">
<head>
	<title>
		<fmt:message key="locationTermsTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/locationTerm/style/links.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/locationTerm/scripts/locationTerms.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a href="${pageContext.request.contextPath}/locationTerm/locationTermsActionMenu.html?offender=${offenderSummary.id}&amp;location=${location.id}&amp;supervisoryOrganization=${supervisoryOrganization.id}&amp;correctionalStatus=${correctionalStatus.id}" class="actionMenuItem" id="actionMenuLink"></a>
		<fmt:message key="locationTermsTitle"/>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>