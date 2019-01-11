<%-- Includes general css if not loaded.
 - Author: Ryan Johns
 - Author: Joel Norris
 - Version: 0.1.1 (July 27, 2018)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/linksCSS.jsp"/>
	<c:if test="${empty generalCSS}">
		<c:set var="generalCSS" value="true" scope="request"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css?PRESENTATION_RESOURCE_VERSION=${initParam['presentationResourceVersion']}"/>
		<c:choose>
			<c:when test="${not empty sessionScope.userAppearance}">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css?USER_APPEARANCE=${sessionScope.userAppearance.date.time}&PRESENTATION_RESOURCE_VERSION=${initParam['presentationResourceVersion']}"/>
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/userPreference.css?USER_APPEARANCE=${sessionScope.userAppearance.date.time}&PRESENTATION_RESOURCE_VERSION=${initParam['presentationResourceVersion']}"/>
			</c:when>
			<c:otherwise>
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css?PRESENTATION_RESOURCE_VERSION=${initParam['presentationResourceVersion']}"/>
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/userPreference.css?USER_APPEARANCE=${sessionScope.userAppearance.date.time}&PRESENTATION_RESOURCE_VERSION=${initParam['presentationResourceVersion']}"/>
			</c:otherwise>
		</c:choose>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css?PRESENTATION_RESOURCE_VERSION=${initParam['presentationResourceVersion']}"/>
	</c:if>