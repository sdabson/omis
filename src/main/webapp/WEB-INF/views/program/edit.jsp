<?xml version="1.0" encoding="UTF-8"?>
<%--
  - Screen to edit program placements.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty programPlacement}">
					<fmt:message key="editProgramPlacementTitle" bundle="${programBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createProgramPlacementTitle" bundle="${programBundle}"/>
				</c:otherwise>
			</c:choose>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/program/scripts/programPlacement.js?VERSION=1"> </script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/program/programPlacementActionMenu.html?offender=${offenderSummary.id}" id="actionMenuLink" class="actionMenuItem"></a>
			<c:choose>
				<c:when test="${not empty programPlacement}">
					<fmt:message key="editProgramPlacementTitle" bundle="${programBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createProgramPlacementTitle" bundle="${programBundle}"/>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</html>