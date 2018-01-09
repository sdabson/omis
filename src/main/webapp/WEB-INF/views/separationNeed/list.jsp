<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listResources.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/separationNeed/scripts/separationNeeds.js"> </script>
	<title>
		<fmt:message key="separationNeedListHeader">
		</fmt:message>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="separationNeedsActionMenuLink" href="${pageContext.request.contextPath}/separationNeed/separationNeedsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="separationNeedListHeader"/>
	</h1>
	<c:set scope="request" value="${offender}" var="offender"/>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>