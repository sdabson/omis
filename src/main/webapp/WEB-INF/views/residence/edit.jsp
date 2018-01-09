<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.residence.msgs.residence">
<head>
	<title>
	<c:choose>
		<c:when test="${not empty residenceTerm || not empty nonResidenceTerm}">
			<fmt:message key="editResidenceTitle">
				<fmt:param>
					<fmt:message key="residenceStatusOptionLabel.${residenceStatusOption.name}"/>
				</fmt:param>
			</fmt:message>
		</c:when>
		<c:otherwise>
			<fmt:message key="createResidenceTitle">
				<fmt:param>
					<fmt:message key="residenceStatusOptionLabel.${residenceStatusOption.name}"/>
				</fmt:param>
			</fmt:message>
		</c:otherwise>
	</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/residence/scripts/JQuery/jquery.omis.residence.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/residence/scripts/residence.js?VERSION=1"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/scripts/searchUserAccount.js?VERSION=1"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>	
	<h1>	
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/residence/residenceActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty residenceTerm || not empty nonResidenceTerm}">
				<fmt:message key="editResidenceTitle">
					<fmt:param>
						<fmt:message key="residenceStatusOptionLabel.${residenceStatusOption.name}"/>
					</fmt:param>
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="createResidenceTitle">
					<fmt:param>
						<fmt:message key="residenceStatusOptionLabel.${residenceStatusOption}"/>
					</fmt:param>
				</fmt:message>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>	
</body>
</fmt:bundle>
</html>