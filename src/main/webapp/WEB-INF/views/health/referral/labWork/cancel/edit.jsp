<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Joel Norris
 - Version: 0.1.0 (Aug 20, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.health.msgs.health">
<head>
	<title><fmt:message key="cancelLabWorkReferralTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/health/style/health.css"/>
</head>
<body>
	<h1><fmt:message key="cancelLabWorkReferralTitle"/></h1>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<ul class="toolbar">
		<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_CENTER')">
		<li>
			<a id="referralCenterLink" class="healthLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html?referralType=ALL&amp;facility=${facility.id}">
				<fmt:message key="title">
					<fmt:param value="${facility.name}"/>
				</fmt:message>
			</a>
		</li>
		</sec:authorize>
	</ul>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>