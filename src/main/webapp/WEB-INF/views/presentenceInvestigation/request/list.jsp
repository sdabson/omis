<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<title>
		<fmt:message key="presentenceInvestigationRequestListHeader"/>
	</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/presentenceInvestigation/scripts/presentenceInvestigationRequests.js?VERSION=1"> </script>
</head>
 <body>
 	<c:choose>
	<c:when test="${empty offender}">
		<c:set var="onReturn" value="byUser" />
	</c:when>
	<c:when test="${not empty offender}">
		<c:set var="onReturn" value="byOffender" />
		<c:if test="${not empty offenderSummary}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:if>
	</c:when>
	</c:choose>
 	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/presentenceInvestigationRequestsActionMenu.html?assignedUser=${assignedUser.id}&offender=${offender.id}&onReturn=${onReturn}"></a>
		<fmt:message key="presentenceInvestigationRequestListHeader"/>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>