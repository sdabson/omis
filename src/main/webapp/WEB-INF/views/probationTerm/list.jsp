<%--
 - Author: Josh Divine
 - Version: 0.1.0 (May 25, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
<head>
	<title>
		<fmt:message key="probationTermsTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/probationTerm/scripts/probationTerms.js"> </script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<c:if test="${not empty courtCase}">
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/probationTerm/probationTermsActionMenu.html?courtCase=${courtCase.id}"></a>
		</c:if>
		<fmt:message key="probationTermsTitle"/>
	</h1>
	<c:if test="${not empty courtCase}">
		<h2>
			<fmt:message key="probationTermCauseNumberLabel"/> <c:out value="${courtCase.docket.value}"/>
		</h2>
	</c:if>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>