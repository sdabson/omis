<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.victim.msgs.victim">
<head>
	<title>
		<fmt:message key="victimProfileTitle"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
</head>
<body>
	<c:set value="${contactSummary}" var="contactSummary" scope="request"/>
	<jsp:include page="includes/victimHeader.jsp"/>
	<h1><fmt:message key="victimProfileTitle"/></h1>
	<div id="victimProfileLinks" class="foreground">
		<ul id="profileLinks" class="profileLinks">
			<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_ASSOCIATION_LIST')">
				<li><a class="hoverable accentLight"  href="${pageContext.request.contextPath}/victim/association/listByVictim.html?victim=${victimSummary.id}"><fmt:message key="victimAssociationsLinkAndCount"><fmt:param value="${victimSummary.associationCount}"/></fmt:message></a></li>
			</sec:authorize>
			<c:choose>
				<c:when test="${victimSummary.offender}">
					<sec:authorize access="hasRole('DISABLED')">
						<li><a class="hoverable accentLight"  href="${pageContext.request.contextPath}/offenderContact/edit.html?offender=${victimSummary.id}"><fmt:message key="victimContactLink"/></a></li>
					</sec:authorize>
				</c:when>
				<c:otherwise>
					<sec:authorize access="hasRole('DISABLED')">
						<li><a class="hoverable accentLight"  href="${pageContext.request.contextPath}/victim/contact/edit.html?victim=${victimSummary.id}"><fmt:message key="victimContactLink"/></a></li>
					</sec:authorize>
				</c:otherwise>
			</c:choose>
			<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_NOTE_LIST')">
				<li><a class="hoverable accentLight"  href="${pageContext.request.contextPath}/victim/note/list.html?victim=${victimSummary.id}"><fmt:message key="victimNotesLinkAndCount"><fmt:param value="${victimSummary.noteCount}"/></fmt:message></a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_DOCUMENT_VIEW')">
				<li><a class="hoverable accentLight"  href="${pageContext.request.contextPath}/victim/document/edit.html?victim=${victimSummary.id}"><fmt:message key="victimDocumentsLinkAndCount"><fmt:param value="${victimSummary.documentCount}"/></fmt:message></a></li>
			</sec:authorize>
		</ul>
	</div>
</body>
</fmt:bundle>
</html>