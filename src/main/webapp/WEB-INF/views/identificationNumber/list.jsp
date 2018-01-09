<?xml version="1.0" encoding="UTF-8"?>
<%--
  - Listing screen for identification numbers.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle basename="omis.identificationnumber.msgs.identificationNumber" var="identificationNumberBundle"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/identificationNumber/scripts/identificationNumbers.js?VERSION=1"> </script>
		<title>
			<fmt:message key="listIdentificationNumberTitle" bundle="${identificationNumberBundle}"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/identificationNumber/identificationNumbersActionMenu.html?offender=${offenderSummary.id}" class="actionMenuItem" id="actionMenuLink"></a>
			<fmt:message key="listIdentificationNumberTitle" bundle="${identificationNumberBundle}"/>
		</h1>
		<jsp:include page="includes/listTable.jsp"/>
	</body>
</html>