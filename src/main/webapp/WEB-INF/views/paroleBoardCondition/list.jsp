<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
<head>
	<title>
		<fmt:message key="paroleBoardConditionHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/paroleBoardCondition/style/paroleBoardCondition.css?VERSION=1"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardCondition/scripts/JQuery/jquery.omis.paroleBoardConditions.js?Version=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardCondition/scripts/paroleBoardConditions.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/paroleBoardCondition/paroleBoardAgreementsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="paroleBoardConditionHeader"/>
	</h1>
	<jsp:include page="includes/paroleBoardAgreementDateRangeForm.jsp"/>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>