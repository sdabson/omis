<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.msgs.index">
<head>
<title><fmt:message key="${headerKey}"/></title>
<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
<script type="text/javascript">
/* <![CDATA[ */
window.onload = function() {
	document.getElementById("offenderNumber").focus();
};
/* ]]> */
</script>
</head>
<body>
	<h1><fmt:message key="${headerKey}"/></h1>
	<p>
		<fmt:message key="pleaseLoadOffenderMessage"/>
	</p>
	<jsp:include page="includes/showProfilePrompt.jsp">
		<jsp:param name="offenderNumberFieldName" value="offenderNumber" />
	</jsp:include>
	<ul>
		<sec:authorize access="hasRole('OFFENDER_CREATE') or hasRole('ADMIN')">
			<li class="taskLinkItem">
				<a class="createLink" href="${pageContext.request.contextPath}/offender/create.html">
					<fmt:message key="createOffenderLink"/>
				</a>
			</li>
		</sec:authorize>
	</ul>
</body>
</fmt:bundle>
</html>