<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<head>
	<title>
		<fmt:message key="adaAccommodationsTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/adaAccommodation/scripts/JQuery/jquery.omis.accommodations.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/adaAccommodation/scripts/accommodations.js?VERSION=1"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/adaAccommodation/accommodationsActionMenu.html?offender=${offender.id}"></a><fmt:message key="adaAccommodationHeader"/>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>	
</body>
</fmt:bundle>
</html>