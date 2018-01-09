<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.locationterm.msgs.locationTerm" var="locationTermBundle"/>
<fmt:setBundle basename="omis.locationterm.msgs.locationReasonTerm" var="locationReasonTermBundle"/>
<fmt:bundle basename="omis.locationterm.msgs.profile">
<head>
	<title><fmt:message key="locationTermProfileHeader"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderHeaderResources.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1><fmt:message key="locationTermProfileHeader"/></h1>
	<ul class="taskLinks">
		<sec:authorize access="hasRole('LOCATION_TERM_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/locationTerm/list.html?offender=${offenderSummary.id}"><fmt:message key="listLocationTermsLink" bundle="${locationTermBundle}"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('LOCATION_REASON_TERM_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/locationTerm/locationReasonTerm/list.html?offender=${offenderSummary.id}"><fmt:message key="listLocationReasonTermsLink" bundle="${locationReasonTermBundle}"/></a></li>
		</sec:authorize>
	</ul>
</body>
</fmt:bundle>
</html>