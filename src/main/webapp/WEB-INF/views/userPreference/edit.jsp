<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.userpreference.msgs.userPreference">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/userPreference/style/userPreference.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/userPreference/scripts/jquery/jquery.omis.userPreference.js?VERSION=1"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/userPreference/scripts/userPreference.js?VERSION=1"> </script>
	<title>
		<fmt:message key="userPreferenceTitle"/>
	</title>
</head>
<body>
	<h1>
		<fmt:message key="userPreferenceTitle"/>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>