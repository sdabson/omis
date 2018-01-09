<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">      
	<fmt:bundle basename="omis.employment.msgs.employment">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>	
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/style/general.css" type="text/css" media="all" />
		<script src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery-ui-custom.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/EventRunner.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/Edit.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/ServerConfig.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/userSearch.js" type="text/javascript"></script>
		                                               
		
		<title><fmt:message key="employmerDetailScreenName"/> - ${offenderNumber}</title>
	</head>
	<body>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
	</fmt:bundle>
</html>