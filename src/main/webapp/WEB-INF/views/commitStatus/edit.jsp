<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Yidong Li
 - Version: 0.1.0 (June 6, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
	<fmt:setBundle basename="omis.commitstatus.msgs.commitStatus" var="commitStatus"/>
	<head>
		<title>
			<c:choose>
				<c:when test="${flag}">  
					<fmt:message key="createCommitStatusesLabel" bundle="${commitStatus}"></fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:when>	
				<c:otherwise>
					<fmt:message key="editCommitStatusesLabel" bundle="${commitStatus}"></fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:otherwise>	
			</c:choose>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/contact/includes/poBoxFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/personFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/commitStatus/scripts/commitStatusEdit.js"></script>
  	</head>
	<body>
		<c:if test="${not empty offenderSummary}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:if>
		<h1>
			<a class="actionMenuItem" id="commitStatusCreateEditScreenActionMenuLink" href="${pageContext.request.contextPath}/commitStatus/commitStatusEditActionMenu.html?offender=${offenderSummary.id}"></a><span class="visibleLinkLabel"/>
			<c:if test="${flag}">
				<fmt:message key="createCommitStatusesLabel" bundle="${commitStatus}"></fmt:message>
			</c:if>
			<c:if test="${not flag}">
				<fmt:message key="editCommitStatusesLabel" bundle="${commitStatus}"></fmt:message>
			</c:if>
		</h1>
		<jsp:include page="/WEB-INF/views/commitStatus/includes/editFormNew.jsp"/>
	</body>
</html>