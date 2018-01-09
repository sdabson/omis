<?xml version="1.0" encoding="UTF-8"?>
<%--
  - Screen to create or edit identification numbers.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.identificationnumber.msgs.identificationNumber" var="identificationNumberBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/sessionConfigResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/identificationNumber/scripts/identificationNumber.js?VERSION=1"> </script>
		<title>
			<c:choose>
				<c:when test="${not empty identificationNumber}">
					<fmt:message key="editIdentificationNumberTitle" bundle="${identificationNumberBundle}"/>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createIdentificationNumberTitle" bundle="${identificationNumberBundle}"/>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/identificationNumber/identificationNumberActionMenu.html?offender=${offenderSummary.id}" class="actionMenuItem" id="actionMenuLink"></a>
			<c:choose>
				<c:when test="${not empty identificationNumber}">
					<fmt:message key="editIdentificationNumberTitle" bundle="${identificationNumberBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createIdentificationNumberTitle" bundle="${identificationNumberBundle}"/>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</html>