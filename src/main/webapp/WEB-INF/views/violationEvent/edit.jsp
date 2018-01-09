<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/violationEvents/style/violationEvent.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/violationEvents/scripts/includes/jquery.omis.violationEvent.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/violationEvents/scripts/violationEvent.js?VERSION=1"></script>
	<script type="text/javascript">
		var currentDisciplinaryCodeViolationItemIndex = ${disciplinaryCodeViolationItemIndex};
		var currentConditionViolationItemIndex = ${conditionViolationItemIndex};
		var currentViolationEventNoteItemIndex = ${violationEventNoteItemIndex};
		var currentViolationEventDocumentItemIndex = ${violationEventDocumentItemIndex};
		var currentDocumentTagItemIndexes = [];
		<c:forEach items="${documentTagItemIndexes}" var="id">
			currentDocumentTagItemIndexes.push(parseInt("${id}"));
		</c:forEach>
	</script>
	<title>
		<c:if test="${empty violationEvent}">
			<fmt:message key="create${category}ViolationEventHeader" />
		</c:if>
		<c:if test="${not empty violationEvent}">
			<fmt:message key="edit${category}ViolationEventHeader" />
		</c:if>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/violationEvent/violationEventActionMenu.html?offender=${offender.id}"></a>
		<c:if test="${empty violationEvent}">
			<fmt:message key="create${category}ViolationEventHeader" />
		</c:if>
		<c:if test="${not empty violationEvent}">
			<fmt:message key="edit${category}ViolationEventHeader" />
		</c:if>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>