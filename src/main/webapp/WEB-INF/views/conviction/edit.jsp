<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Screen to edit convictions.
 -
 - Author: Stephen Abson
 - Author: Josh Divine
 - Version: 0.1.1 (May 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.conviction.msgs.conviction">
<head>
	<title>
		<fmt:message key="convictionTitle">
			<fmt:param value="${courtCase.docket.value}"/>
		</fmt:message>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeaderResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link href="${pageContext.request.contextPath}/resources/conviction/style/conviction.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/conviction/scripts/JQuery/jquery.omis.conviction.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/conviction/scripts/conviction.js"></script> 
	<script type="text/javascript">
		/* <![CDATA[ */
			//Track current conviction index
			var currentConvictionIndex = ${currentConvictionIndex};
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
	<fmt:setBundle basename="omis.courtcase.msgs.courtCase" var="courtCaseBundle"/>
	<%--<c:if test="${empty offenderSummary}">
		<div id="courtCase">
			<span class="fieldGroup">
				<span class="fieldLabel">
					<fmt:message key="defendantLabel" bundle="${courtCaseBundle}"/></span>
				<span class="fieldValue">
					<c:out value="${courtCase.docket.person.name.lastName}, ${courtCase.docket.person.name.firstName}"/></span>
			</span>
			<span class="fieldGroup">
				<span class="fieldLabel">
					<fmt:message key="pronouncementDateLabel" bundle="${courtCaseBundle}"/></span>
				<span class="fieldValue">
					<fmt:formatDate value="${courtCase.pronouncementDate}" type="date"/></span>
			</span>
			<span class="fieldGroup">
				<span class="fieldLabel">
					<fmt:message key="courtLabel" bundle="${courtCaseBundle}"/></span>
				<span class="fieldValue">
					<c:out value="${courtCase.court.location.organization.name}"/></span>
			</span>
			<span class="fieldGroup">
				<span class="fieldLabel">
					<fmt:message key="judgeLabel" bundle="${courtCaseBundle}"/></span>
				<span class="fieldValue">
					<c:out value="${courtCase.personnel.judge.name.lastName}, ${courtCase.personnel.judge.name.firstName}"/></span>
			</span>
		</div>
	</c:if> --%>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/courtCase/courtCaseActionMenu.html?defendant=${courtCase.docket.person.id}"></a>
		<fmt:message key="convictionTitle">
			<fmt:param value="${courtCase.docket.value}"/>
		</fmt:message>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>