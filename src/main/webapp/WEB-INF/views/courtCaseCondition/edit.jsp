<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Jonny Santy
 - Author: Annie Wahl
 - Version: 0.1.2 (Dec 5, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
<head><jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeaderResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCaseCondition/scripts/JQuery/jquery.omis.courtCaseCondition.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCaseCondition/scripts/courtCaseCondition.js?VERSION=3"></script>
	<script type="text/javascript">
		var currentAgreementAssociableDocumentItemIndex = ${agreementAssociableDocumentItemIndex};
		var currentDocumentTagItemIndexes = [];
		<c:forEach items="${documentTagItemIndexes}" var="id">
			currentDocumentTagItemIndexes.push(parseInt("${id}"));
		</c:forEach>
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/courtCaseCondition/style/courtCaseCondition.css" />
	<title>
		<c:choose>
			<c:when test="${not empty courtCaseAgreement}">
				<fmt:message key="editCourtCaseconditionHeader">
					<fmt:param value="${courtCaseAgreementCategory.name}" />
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="createCourtCaseconditionHeader">
					<fmt:param value="${courtCaseAgreementCategory.name}" />
				</fmt:message>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/courtCaseCondition/courtCaseConditionActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty courtCaseAgreement}">
				<fmt:message key="editCourtCaseconditionHeader">
					<fmt:param value="${courtCaseAgreementCategory.name}" />
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="createCourtCaseconditionHeader">
					<fmt:param value="${courtCaseAgreementCategory.name}" />
				</fmt:message>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>