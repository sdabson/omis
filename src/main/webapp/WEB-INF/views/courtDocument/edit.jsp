<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 4, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.courtdocument.msgs.document">
	<head>		
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/document/includes/documentTagResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/eventRunnerResources.jsp"/>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtDocument/scripts/courtCaseDocumentAssociation.js?VERSION=2"></script>
		<title>
			<fmt:message key="courtCaseDocumentFormTitleLabel"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/courtCase/document/createActionMenu.html?offender=${offenderSummary.id}" class="actionMenuItem"></a>
			<fmt:message key="headerLabel"/>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</fmt:bundle>
</html>