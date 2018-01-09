<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome" var="psiBundle" />
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/presentenceInvestigation/style/presentenceInvestigation.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/includes/jquery.omis.healthSectionSummary.js?VERSION=1" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/healthSectionSummary.js?VERSION=1" ></script>
	<script type="text/javascript">
		var currentHealthSectionSummaryNoteItemIndex = ${healthSectionSummaryNoteItemIndex};
		var currentHealthSectionSummaryDocumentAssociationItemIndex = ${healthSectionSummaryDocumentAssociationItemIndex};
		var currentDocumentTagItemIndexes = [];
		<c:forEach items="${documentTagItemIndexes}" var="id">
			currentDocumentTagItemIndexes.push(parseInt("${id}"));
		</c:forEach>
	</script>
	<title>
		<fmt:message key="healthSummaryHeaderTitle" />
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/healthSummary/healthSectionSummaryActionMenu.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}"></a>
		<fmt:message key="presentenceInvestigationDetailsTitle" bundle="${psiBundle}" />
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>