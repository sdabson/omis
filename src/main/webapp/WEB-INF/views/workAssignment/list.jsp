<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
<head>
	<title>
		<fmt:message key="workAssignmentListHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<script type="text/javascript">
		var offender = ${offender.id};
	</script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/workAssignment/scripts/listWorkAssignment.js"> </script>
</head>
 <body>
 	<c:if test="${not empty offenderSummary}">
 	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
 	</c:if>
 	<h1>
		<a class="actionMenuItem" id="workAssignmentListActionMenuLink" href="${pageContext.request.contextPath}/workAssignment/workAssignmentListActionMenu.html?offender=${offender.id}"></a><span class="visibleLinkLabel"/>
		<fmt:message key="workAssignmentListHeader"/>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>