<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 4, 2015)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.placementscreening.msgs.form">
	<head>	
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/placementScreening/style/referral.css?VERSION=1"></link>
		<script>
			var offenderId = ${offenderSummary.id};
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/placementScreening/scripts/JQuery/jquery.omis.referral.js?VERSION=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/placementScreening/scripts/referral.js?VERSION=1"></script>
		<title>
		<c:choose>
		<c:when test="${not empty placementReferral}"><fmt:message key="editPlacementReferralTitle"/></c:when>
		<c:otherwise>
			<fmt:message key="createPlacementReferralTitle"/>
		</c:otherwise>
		</c:choose>
		</title>	
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/placementScreening/referral/referralActionMenu.html"></a>
			<c:choose>
		<c:when test="${not empty placementReferral}"><fmt:message key="editPlacementReferralTitle"/></c:when>
		<c:otherwise>
			<fmt:message key="createPlacementReferralTitle"/>
		</c:otherwise>
		</c:choose>			
		</h1>
		<jsp:include page="includes/editForm.jsp"/>	
	</body>
</fmt:bundle>
</html>