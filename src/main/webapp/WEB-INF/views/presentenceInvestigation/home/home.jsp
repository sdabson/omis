<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
<head>
	<title>
		<fmt:message key="presentenceInvestigationHomeTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/presentenceInvestigation/style/presentenceInvestigation.css?VERSION=3" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/includes/jquery.omis.home.js?VERSION=1" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/home.js?VERSION=1" ></script>
	<script type="text/javascript">
		var currentPresentenceInvestigationRequestNoteItemIndex = ${presentenceInvestigationRequestNoteItemIndex};
	</script>
</head>
<body>
		<c:choose>
			<c:when test="${not empty offender}">
				<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
			</c:when>
		</c:choose>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink"
				href="${pageContext.request.contextPath}/presentenceInvestigation/homeActionMenu.html?presentenceInvestigationRequest=${summary.presentenceInvestigationRequestId}&onReturn=home"></a>
			<fmt:message key="presentenceInvestigationHomeTitle"/>
		</h1>
		<jsp:include page="includes/homeContent.jsp"/>
</body>
</fmt:bundle>
</html>