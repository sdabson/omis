<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Aug 19, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
	<fmt:setBundle basename="omis.employment.msgs.employment" var="employmentBundle"/>
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty employmentTerm}">  
					<fmt:message key="updateEmployerLabel" bundle="${employmentBundle}"></fmt:message>
				</c:when>	
				<c:otherwise>
					<fmt:message key="createEmploymentLabel" bundle="${employmentBundle}"></fmt:message>
				</c:otherwise>
			</c:choose>
		</title>
		<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="X-UA-Compatible" content="IE10"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/links.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/toolbar.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/form.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/offender/style/offenderHeader.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/audit/style/audit.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
 		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/employment/scripts/JQuery/jquery.omis.changeEmployer.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/employment/scripts/changeEmployer.js"></script>
		<script>
			var createAddress = ${createAddress};
			var createEmployer = ${createEmployer};
		</script>
	</head>
	<body>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/employment/updateEmployerActionMenu.html?employmentTerm=${employerChangeForm.employmentTerm.id}"></a><span class="visibleLinkLabel"/>
			<fmt:message key="updateEmployerLabel" bundle="${employmentBundle}"></fmt:message>
		</h1>
		<jsp:include page="/WEB-INF/views/employment/includes/changeEmployerForm.jsp"/>
	</body>
</html>