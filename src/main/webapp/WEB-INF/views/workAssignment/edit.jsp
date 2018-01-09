<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Aug 26, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
	<fmt:setBundle basename="omis.workassignment.msgs.workAssignment" var="workAssignment"/>
	<head>
		<title>
			<c:choose>
				<c:when test="${createWorkAssignment}">  
					<fmt:message key="createWorkAssignmentLabel" bundle="${workAssignment}"></fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:when>	
				<c:otherwise>
					<fmt:message key="updateWorkAssignmentLabel" bundle="${workAssignment}"></fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:otherwise>	
			</c:choose>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/linksResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/workAssignment/scripts/JQuery/jquery.omis.createWorkAssignmentNotes.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/workAssignment/scripts/workAssignment.js"></script>
		<script type="text/javascript">
  			var workAssignmentNoteIndex= ${workAssignmentNoteIndex};
  			var originalWorkAssignmentNoteIndex= ${originalWorkAssignmentNoteIndex};
	  	</script>
	</head>
	<body>
		<c:if test="${not empty offenderSummary}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:if>
		<h1>
			<a class="actionMenuItem" id="workAssignmentEditActionMenuLink" href="${pageContext.request.contextPath}/workAssignment/workAssignmentEditActionMenu.html?offender=${offenderSummary.id}"></a><span class="visibleLinkLabel"/>
			<c:choose>
				<c:when test="${createWorkAssignment}">  
					<fmt:message key="createWorkAssignmentLabel" bundle="${workAssignment}"></fmt:message>
				</c:when>	
				<c:otherwise>
					<fmt:message key="updateWorkAssignmentLabel" bundle="${workAssignment}"></fmt:message>
				</c:otherwise>	
			</c:choose>
		</h1>
		<jsp:include page="/WEB-INF/views/workAssignment/includes/editForm.jsp"/>
	</body>
</html>