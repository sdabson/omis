<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
		"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<fmt:bundle basename="omis.employment.msgs.employment">
	<head>
		<title><fmt:message key="employerListingHeader"/></title>
	</head>
	<body>
		<h1><fmt:message key="employerListingHeader"/></h1>
		<ul id="employmentToolbar">
			<li><a href="#"><fmt:message key="newEmployerLabel"/></a></li>
			<li><a href="#"><fmt:message key="employmentHelpLabel"/></a></li>
		</ul>
		<jsp:include page="includes/listTable.jsp"/>	
	</body>
	</fmt:bundle>
</html>