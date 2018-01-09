<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Sept 9, 2015)
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
					<fmt:message key="updateEmploymentLabel" bundle="${employmentBundle}"></fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:when>	
				<c:otherwise>
					<fmt:message key="createEmploymentLabel" bundle="${employmentBundle}"></fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery-ui.custom.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/ServerConfig.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/jquery.min.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/MessageResolver.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery-ui-custom.min.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.search.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/tools.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/search.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/form.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/scripts/searchUserAccount.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/employment/scripts/employment.js"></script>
		<script>
			var create = ${create};
			var newAddress = ${newAddress};
			var employerStatus = ${employerStatus};
		</script>
	</head>
	<body>
		<c:if test="${not empty offenderSummary}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:if>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/employment/employmentActionMenu.html?offender=${offenderSummary.id}&employerStatus=${employerStatus}&employmentTerm=${employmentTerm.id}"></a><span class="visibleLinkLabel"/>
			<c:choose>
				<c:when test="${not empty employmentTerm}">  
					<fmt:message key="updateEmploymentLabel" bundle="${employmentBundle}"></fmt:message>
				</c:when>	
				<c:otherwise>
					<fmt:message key="createEmploymentLabel" bundle="${employmentBundle}"></fmt:message>
				</c:otherwise>	
			</c:choose>
		</h1>
		<jsp:include page="/WEB-INF/views/employment/includes/createForm.jsp"/>
	</body>
</html>