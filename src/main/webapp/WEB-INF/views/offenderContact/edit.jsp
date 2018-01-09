<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Josh Divine
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offendercontact.msgs.offenderContact">
<head>
	<title>
		<fmt:message key="offenderContactInformationTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/contact/includes/poBoxFieldsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderContact/scripts/offenderContact.js"> </script>
	<script type="text/javascript">
		var telephoneNumberItemIndex = ${not empty telephoneNumberItemIndex ? telephoneNumberItemIndex : null};
		var onlineAccountItemIndex = ${not empty onlineAccountItemIndex ? onlineAccountItemIndex : null};
	</script>
</head>
<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offenderContact/offenderContactActionMenu.html?offender=${offenderSummary.id}"></a>
		<fmt:message key="offenderContactInformationTitle"/>
	</h1>
	<jsp:include page="includes/editForm.jsp" />
</body>
</fmt:bundle>
</html>