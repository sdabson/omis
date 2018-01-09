<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Jonny Santy
 - Author: Annie Jacques
 - Version: 0.1.2 (Aug 30, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
<head>
	<title>
		<fmt:message key="conditionHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/courtCaseCondition/style/courtCaseCondition.css?VERSION=1"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCaseCondition/scripts/JQuery/jquery.omis.courtCaseConditions.js?Version=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCaseCondition/scripts/courtCaseConditions.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/courtCaseCondition/courtCaseConditionsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="conditionHeader"/>
	</h1>
	<jsp:include page="includes/courtCaseConditionDateRangeForm.jsp"/>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>