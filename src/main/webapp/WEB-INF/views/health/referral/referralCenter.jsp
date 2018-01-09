<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Mar 31, 2014)
 - Since: OMIS 3.0
 --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.health.msgs.health">
<head>
	<title>
		<fmt:message key="title">
			<fmt:param value="${facility.name}"/>
		</fmt:message>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/referralCenter.css?VERSION=1"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/dataTables/jquery.dataTables.min.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/JQuery/jquery.omis.referralCenter.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/referralCenter.js?VERSION=1"></script>
</head>
<body>
	<div class="header">
	<div class="headerTitle">
	<fmt:message key="title" var="title">
		<fmt:param value="${facility.name}"/>
	</fmt:message>
	</div>
		<div class="titleHeader">
			<a href="${pageContext.request.contextPath}/health/referral/actionMenu.html?facility=${facility.id}&amp;referralType=${referralType.name}" id="referralTitle" class="actionMenuItem"></a>
			<c:out value="${title}"></c:out>
		</div>
	</div>
	<c:set var="offender" value="${filterByOffender}" scope="request"/>
	<div id="facilityReferralCenterFilter">
		<jsp:include page="includes/filterForm.jsp"/>
	</div>
	<div id="facilityReferralCenterFilterMin" >
		<span><fmt:message key="filterToolsTitle"/></span>
	</div>
	<div id="referrals">
	<c:choose>
		<c:when test="${referralType.name eq 'ALL' or referralType.name eq 'INTERNAL_MEDICAL' or referralType.name eq 'EXTERNAL_MEDICAL' or referralType.name eq 'LAB' or empty referralType}">
			<jsp:include page="includes/referrals.jsp"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="unsupportedReferralTypeMessage">
				<fmt:param>${referralType.name}</fmt:param>
			</fmt:message>
		</c:otherwise>
	</c:choose>
	</div>
</body>
</fmt:bundle>
</html>