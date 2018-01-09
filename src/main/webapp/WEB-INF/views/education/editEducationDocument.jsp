<?xml version="1.0" encoding="UTF-8"?>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.education.msgs.educationDocument">
	<head>		
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/document/includes/documentTagResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/eventRunnerResources.jsp"/>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/education/scripts/educationDocument.js?VERSION=2"></script>
		<title>
			<fmt:message key="educationDocumentTitle"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/education/document/educationDocumentActionMenu.html?offender=${offenderSummary.id}" id="actionMenuLink" class="actionMenuItem"></a>
			<fmt:message key="educationDocumentTitle"/>
		</h1>
		<jsp:include page="includes/editEducationDocumentForm.jsp"/>
	</body>
</fmt:bundle>
</html>