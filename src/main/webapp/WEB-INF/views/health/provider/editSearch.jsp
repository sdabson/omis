<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.health.msgs.provider" var="providerBundle"/>
<fmt:bundle basename="omis.msgs.homeLinks">
<head>
	<title>	
	<fmt:message key="providerSearchTitle" bundle="${providerBundle}"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
</head>
<body>	
	<sec:authorize access="hasRole('ADMIN') or hasRole('USER') or hasRole('APP_DEV')">
	<h1>			
		<a class="actionMenuItem" id="searchCriteriaActionMenu" href="${pageContext.request.contextPath}/home.html"></a>
<%-- 		<a class="actionMenuItem" id="searchCriteriaActionMenu" href="${pageContext.request.contextPath}/providerSearch/searchCriteriaActionMenu.html"></a> --%>
		<fmt:message key="providerSearchTitle" bundle="${providerBundle}"/>
	</h1>
	</sec:authorize>	
		<jsp:include page="includes/editFormSearch.jsp"/>
	<h1>
			<a class="actionMenuItem" id="searchCriteriaActionMenu" href="${pageContext.request.contextPath}/home.html"></a>
<%-- 		<a class="actionMenuItem" id="searchResultsActionMenu" href="${pageContext.request.contextPath}/providerSearch/searchResultsActionMenu.html"></a> --%>
		<fmt:message key="providerSearchResultsTitle" bundle="${providerBundle}"/>
	</h1>	
	<table id="providerSearchResultsNoPhotoListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="providerNameLabel" bundle="${providerBundle}"/></th>
			<th><fmt:message key="serviceCategoryLabel" bundle="${providerBundle}"/></th>
			<th><fmt:message key="addressLabel" bundle="${providerBundle}"/></th>
			<th><fmt:message key="cityLabel" bundle="${providerBundle}"/></th>
			<th><fmt:message key="phoneNumberLabel" bundle="${providerBundle}"/></th>
			<th><fmt:message key="websiteLabel" bundle="${providerBundle}"/></th>
		</tr>
	</thead>	
	</table>
</body>
</fmt:bundle>
</html>