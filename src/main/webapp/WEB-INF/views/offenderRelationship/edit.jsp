<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<head>
	<title>
		<fmt:message key="offenderRelationshipsEditTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/form.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery-ui.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/caseload/style/form.css"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/personFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/contact/includes/poBoxFieldsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/JQuery/update/jquery.omis.createOffenderRelationshipEditTelephoneNumber.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/JQuery/update/jquery.omis.createOffenderRelationshipEditEmail.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/editOffenderRelationships.js"></script>
	<script type="text/javascript">
		var offender = ${offender.id};
		var offenderRelationshipTelephoneNumberIndex= ${offenderRelationshipTelephoneNumberIndex};
		var offenderRelationshipOnlineAccountIndex= ${offenderRelationshipOnlineAccountIndex};
	</script>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<h1>
		<a class="actionMenuItem" id="offenderRelationshipListActionMenuLink" href="${pageContext.request.contextPath}/offenderRelationship/update/offenderRelationListActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="offenderRelationshipsEditTitle"/>
	</h1>
	<jsp:include page="/WEB-INF/views/offenderRelationship/includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>