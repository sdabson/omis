<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 04, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.health.msgs.health">
<head>
	<title><fmt:message key="providersTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/referralCenter.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/dataTables/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/health/scripts/referral/referralCenter.js"></script>
</head>
<body>
	<h1><fmt:message key="providersTitle"/></h1>
	<ul id="providersToolbar" class="toolbar">
		<li>
			<a class="healthLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html?facility=${facility.id}&referralType=ALL">
			<fmt:message key="facilityReferralCenterLink">
			<fmt:param value="${facility.name}"/>
			</fmt:message>					
			</a>
		</li>
		<li>
			<sec:authorize access="hasRole('APP_DEV')">
			<a class="createLink" href="${pageContext.request.contextPath}/health/provider/create.html?facility=${facility.id}">
				<span class="visibleLinkLabel"><fmt:message key="createProviderAssignmentLabel"/></span>
			</a>
			</sec:authorize>
		</li>
	</ul>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>