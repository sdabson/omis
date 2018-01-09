<%-- Includes libraries for scroll container if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Oct 21, 2014)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<c:if test="${empty scrollContainerLIB}">
		<c:set var="scrollContainerLIB}" value="true" scope="request"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/scrollContainer.css?VERSION=2"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.ScrollContainer.js?VERSION=3"></script>
	</c:if>