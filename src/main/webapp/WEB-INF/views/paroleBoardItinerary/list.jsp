<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 30, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
<head>
	<title>
		<fmt:message key="paroleBoardItinerariesTitle"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/paroleBoardItinerary/style/paroleBoardItinerary.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardItinerary/scripts/JQuery/jquery.omis.paroleBoardItineraries.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardItinerary/scripts/paroleBoardItineraries.js?VERSION=1"></script>
</head>
<body>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/paroleBoardItinerariesActionMenu.html"></a>
		<fmt:message key="paroleBoardItinerariesTitle"/>
	</h1>
	<jsp:include page="includes/paroleBoardItinerarySearchForm.jsp"/>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>