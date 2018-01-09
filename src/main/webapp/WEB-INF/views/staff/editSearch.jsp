<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffBundle"/>
<head>
	<title>	
	<fmt:message key="staffSearchTitle" bundle="${staffBundle}"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/home.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/staff/scripts/staffSearch.js?VERSION=1"></script>
</head>
<body>	
	<h1>			
		<a class="actionMenuItem" id="staffSearchCriteriaActionMenu" href="${pageContext.request.contextPath}/staffSearch/searchCriteriaActionMenu.html"></a>
		<fmt:message key="staffSearchTitle" bundle="${staffBundle}"/>
	</h1>
	<jsp:include page="includes/editFormSearch.jsp"/>		
</body>
</html>