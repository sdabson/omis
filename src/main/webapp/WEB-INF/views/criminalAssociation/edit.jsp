<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">
<head>
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE10"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/criminalAssociation/scripts/criminalAssociation.js"> </script>
<title>
	<c:choose>
		<c:when test="${not empty criminalAssociation}">
			<fmt:message key="associationEditTitle">
			</fmt:message>
		</c:when>
		<c:otherwise>
			<fmt:message key="associationCreateTitle">
			</fmt:message>
		</c:otherwise>
	</c:choose>
</title>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<sec:authorize access="hasRole('CRIMINAL_ASSOCIATION_CREATE') or hasRole('ADMIN')">
	<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/criminalAssociation/criminalAssociationActionMenu.html?offender=${offenderSummary.id}"><span class="visibleLinkLabel"/></a>

				<c:choose>
					<c:when test="${not empty criminalAssociation}">  
						<fmt:message key="associationEditTitle"></fmt:message>
					</c:when>	
					<c:otherwise>
						<fmt:message key="associationCreateTitle"></fmt:message>
					</c:otherwise>	
				</c:choose>
	</h1>
	</sec:authorize>
	<jsp:include page="/WEB-INF/views/criminalAssociation/includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>