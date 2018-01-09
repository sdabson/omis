<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
<head>
	<title><fmt:message key="detainerNotificationTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/detainernotification/style/detainerNotification.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/detainernotification/scripts/includes/jquery.omis.detainerNotification.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/detainernotification/scripts/detainerNotification.js?VERSION=1"></script>
	<script type="text/javascript">
		var currentInterstateDetainerActivityItemIndex = ${interstateDetainerActivityItemIndex};
		var currentDetainerNoteItemIndex = ${detainerNoteItemIndex};
		var currentDocumentTagItemIndexes = [];
		<c:forEach items="${documentTagItemIndexes}" var="id">
			currentDocumentTagItemIndexes.push(parseInt("${id}"));
		</c:forEach>
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/detainerNotification/detainerNotificationActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="detainerNotificationTitle"/>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>