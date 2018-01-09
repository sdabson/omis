<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - AUthor: Joel Norris
 - Version: 0.1.1 (November 17, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.condition.msgs.condition">
<head>
	<title><fmt:message key="conditionHeader"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
</head>
<body>
	<h1>
		<fmt:message key="conditionHeader"/>
	</h1>
	
	<jsp:include page="/WEB-INF/views/offender/includes/conditionalOffenderHeader.jsp"/>

	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>