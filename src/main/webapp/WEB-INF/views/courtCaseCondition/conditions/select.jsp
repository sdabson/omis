<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCaseCondition/scripts/conditionsSelect.js?VERSION=1"> </script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/courtCaseCondition/style/courtCaseCondition.css" />
	<title>
		<fmt:message key="selectConditionsHeader">
			<c:choose>
				<c:when test="${not empty conditionGroup}">
					<fmt:param value="${conditionGroup.name}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key='standardConditionLabel' var="standardLabel"/>
					<fmt:param value="${standardLabel}"/>
				</c:otherwise>
			</c:choose> 
		</fmt:message>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/courtCaseCondition/condition/conditionsSelectActionMenu.html?courtCaseAgreement=${courtCaseAgreement.id}"></a>
		<fmt:message key="selectConditionsHeader">
			<c:choose>
				<c:when test="${not empty conditionGroup}">
					<fmt:param value="${conditionGroup.name}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key='standardConditionLabel' var="standardLabel"/>
					<fmt:param value="${standardLabel}"/>
				</c:otherwise>
			</c:choose> 
		</fmt:message>
	</h1>
	<jsp:include page="includes/selectConditionsListTable.jsp"/>
</body>
</fmt:bundle>
</html>