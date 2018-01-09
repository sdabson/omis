<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 5, 2016)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.courtdocument.msgs.document">
	<head>
		<title>
			<fmt:message key="courtCaseDocumentsLabel"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/eventRunnerResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtDocument/scripts/courtCaseDocumentAssociation.js"></script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeader.jsp"/>
		<%-- <h1><a class="actionMenuItem" id="actionMenuLink" href="{pageContext.request.contextPath}/courtCase/document/courtCaseDocumentActionMenu.html?offender=${offender.id}"></a><fmt:message key="courtCaseDocumentsLabel"/></h1> --%>
		<jsp:include page="includes/listTable.jsp"/>
	</body>
</fmt:bundle>
</html>