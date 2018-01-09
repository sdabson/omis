<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<head>
	<title>
	<c:choose>
		<c:when test="${not empty issuance}">
			<fmt:message key="editIssuanceTitle"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="createIssuanceTitle"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</c:otherwise>
	</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/adaAccommodation/scripts/JQuery/jquery.omis.issuance.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/adaAccommodation/scripts/issuance.js?VERSION=1"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/adaAccommodation/accommodationIssuanceActionMenu.html?offender=${offender.id}"></a>
		
		<c:choose>
			<c:when test="${not empty issuance}">
				<fmt:message key="editIssuanceTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createIssuanceTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editFormIssuance.jsp"/>
</body>
</fmt:bundle>
</html>