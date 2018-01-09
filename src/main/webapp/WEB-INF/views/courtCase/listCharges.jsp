<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Aug 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<head>
	<title>
		<fmt:message key="chargesTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/courtCase/scripts/charges.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/courtCase/chargesActionMenu.html?defendant=${defendant.id}"></a>
		<fmt:message key="chargesTitle"/>
	</h1>
	<jsp:include page="includes/listChargesTable.jsp"/>
</body>
</fmt:bundle>
</html>