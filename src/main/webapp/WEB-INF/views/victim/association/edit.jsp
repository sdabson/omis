<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.victim.msgs.victim">
<head>
	<title>
	<c:choose>
		<c:when test="${not empty victimAssociation}">
			<fmt:message key="editVictimAssociationTitle"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="createVictimAssociationTitle"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</c:otherwise>
	</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/personFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/contact/includes/poBoxFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/victimAssociation.js"> </script>
	<script type="text/javascript">
		var victimNoteItemIndex = ${not empty victimNoteItemIndex ? victimNoteItemIndex : null};
		var telephoneNumberItemIndex = ${not empty telephoneNumberItemIndex ? telephoneNumberItemIndex : null};
		var onlineAccountItemIndex = ${not empty onlineAccountItemIndex ? onlineAccountItemIndex : null};
	</script>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<c:if test="${not empty victimSummary}">
		<c:set value="${contactSummary}" var="contactSummary" scope="request"/>
		<jsp:include page="/WEB-INF/views/victim/includes/victimHeader.jsp"/>
	</c:if>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/victim/association/associationActionMenu.html?offender=${offenderSummary.id}&amp;victim=${victimSummary.id}"></a>
		<c:choose>
			<c:when test="${not empty victimAssociation}">
				<fmt:message key="editVictimAssociationTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createVictimAssociationTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp" />
</body>
</fmt:bundle>
</html>