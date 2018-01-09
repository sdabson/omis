<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty courtCase}">
				<fmt:message key="editCourtCaseTitle"/>			
			</c:when>
			<c:otherwise>
				<fmt:message key="createCourtCaseTitle"/>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeaderResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<link href="${pageContext.request.contextPath}/resources/courtCase/style/courtCase.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCase/scripts/JQuery/jquery.omis.courtCase.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCase/scripts/courtCase.js"> </script>
    <script type="text/javascript">
	  /* <![CDATA[ */
			//Track current charge index
			var currentChargeIndex = ${currentChargeIndex};
			var currentCourtCaseNoteIndex = ${currentCourtCaseNoteIndex};
	  /* ]]> */
    </script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty offenderSummary}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:when>
		<c:when test="${not empty contactSummary}">
			<jsp:include page="/WEB-INF/views/contact/includes/contactSummary.jsp"/>
		</c:when>
		<c:otherwise>
			<span>NO OFFENDER OR CONTACT SUMMARY</span>
		</c:otherwise>
	</c:choose>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/courtCase/courtCaseActionMenu.html?defendant=${defendant.id}"></a>
		<c:choose>
			<c:when test="${not empty courtCase}">
				<fmt:message key="editCourtCaseTitle"/>			
			</c:when>
			<c:otherwise>
				<fmt:message key="createCourtCaseTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>