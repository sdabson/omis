<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
		"http://www.w3.org/TR/html4/loose.dtd">
<html>
<fmt:bundle basename="omis.msgs.security">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
<style type="text/css">
	h1.bad-credentials {
		font-size: 24pt;
		margin: 0px;
		padding: 4px;
		background-color: DarkRed;
		color: White;
	}
</style>
<script type="text/javascript">
	/* <![CDATA[ */
	if (top.location != location) {
				top.location.href = document.location.href;
	}
	/* ]]> */
</script>
</head>
<body>
	<h1 class="bad-credentials"><fmt:message key="badCredentialsHeader"/></h1>
	<p>
		<fmt:message key="badCredentialsMessage"/>
	</p>
	<ul>
		<li><a href="${pageContext.request.contextPath}/login.html"><fmt:message key="reattemptLoginLink"/></a></li>
	</ul>
</body>
</fmt:bundle>
</html>