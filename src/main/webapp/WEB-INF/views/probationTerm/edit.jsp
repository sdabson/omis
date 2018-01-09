<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Josh Divine
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty probationTerm}">
				<fmt:message key="editProbationTermTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createProbationTermTitle"/>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/probationTerm/scripts/probationTerm.js"> </script>
</head>

<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<c:choose>
			<c:when test="${not empty probationTerm}">
				<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/probationTerm/probationTermActionMenu.html?offender=${offenderSummary.id}&courtCase=${courtCase.id}"></a>
				<fmt:message key="editProbationTermTitle"/>
			</c:when>
			<c:otherwise>
				<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/probationTerm/probationTermActionMenu.html?courtCase=${courtCase.id}"></a>
				<fmt:message key="createProbationTermTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>