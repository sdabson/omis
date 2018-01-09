<%--
 - Displays screen explaining that user does not exist in application.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
		"http://www.w3.org/TR/html4/loose.dtd">
<html>
<fmt:bundle basename="omis.msgs.security">
<head>
<title><fmt:message key="noUserAccountHeader"/></title>
<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
<style type="text/css">
	h1.noUserAccount {
		font-size: 24pt;
		margin: 0px;
		padding: 4px;
		background-color: DarkRed;
		color: White;
	}
	p.message, h1.noUserAccount {
		padding-left: 4px;
		padding-right: 4px;
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
	<h1 class="noUserAccount"><fmt:message key="noUserAccountHeader"/></h1>
	<p class="message">
		<fmt:message key="noUserAccountMessage"/>
	</p>
	<ul>
		<li><a href="${pageContext.request.contextPath}/login.html"><fmt:message key="reattemptLoginLink"/></a></li>
	</ul>
</body>
</fmt:bundle>
</html>