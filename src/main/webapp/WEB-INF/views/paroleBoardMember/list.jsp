<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 9, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
<head>
	<title>
		<fmt:message key="paroleBoardMembersTitle"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/paroleBoardMember/style/paroleBoardMember.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardMember/scripts/JQuery/jquery.omis.paroleBoardMembers.js?VERSION=1"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/paroleBoardMember/scripts/paroleBoardMembers.js?VERSION=1"> </script>
</head>
<body>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/paroleBoardMember/paroleBoardMembersActionMenu.html"></a>
		<fmt:message key="paroleBoardMembersTitle"/>
	</h1>
	<jsp:include page="includes/paroleBoardMemberSearchForm.jsp"/>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>