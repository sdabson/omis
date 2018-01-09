<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	
	<link rel="stylesheet" type="text/css" 
	href="${pageContext.request.contextPath}/resources/disciplinaryCode/style/disciplinaryCode.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/disciplinaryCode/scripts/disciplinaryCodes.js?VERSION=1"> </script>
	<title>
		<fmt:message key="disciplinaryCodesHeader"/>
	</title>
</head>
 <body>
 	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/disciplinaryCode/disciplinaryCodesActionMenu.html?supervisoryOrganization=${supervisoryOrganization.id}"></a>
		<c:out value="${supervisoryOrganization.name}"/>
	</h1>
	<jsp:include page="includes/codeDateForm.jsp"/>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>