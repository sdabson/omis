<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
<head>
	<title>
		<fmt:message key="editOffenderFlagTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/audit/style/audit.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderFlag/scripts/offenderFlags.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
	<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offenderFlag/offenderFlagActionMenu.html?offender=${offender.id}"></a>
	<fmt:message key="editOffenderFlagTitle"/>
	</h1>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>