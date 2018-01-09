<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome" var="psiBundle" />
<fmt:bundle basename="omis.presentenceinvestigation.msgs.supervisionHistorySectionSummary">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/hearing/style/hearing.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/presentenceInvestigation/style/presentenceInvestigation.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/includes/jquery.omis.supervisionHistorySectionSummary.js?VERSION=1" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/supervisionHistorySectionSummary.js?VERSION=1" ></script>
	<script type="text/javascript">
		var currentSupervisionHistoryNoteItemIndex = ${supervisionHistoryNoteItemIndex};
	</script>
	<title>
		<fmt:message key="supervisionHistorySectionSummaryHeaderTitle" />
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/supervisionHistorySummary/supervisionHistorySummaryActionMenu.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}"></a>
		<fmt:message key="presentenceInvestigationDetailsTitle" bundle="${psiBundle}" />
	</h1>
	<jsp:include page="includes/editForm.jsp"/>		
</body>
</fmt:bundle>
</html>