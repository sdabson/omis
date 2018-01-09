<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.substance.msgs.substance">
<head>
<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
<!-- AWWWW yaaaa -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/substanceTest/style/awesomeSampleStyle.css"/>
<!--  End awww yaaa -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/substanceTest/scripts/jQuery/jquery.omis.substanceTests.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/substanceTest/scripts/substanceTests.js"> </script>
	<title>
		<fmt:message key="substanceListHeader">
		</fmt:message>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/substanceTest/substanceTestsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="substanceListHeader">
		</fmt:message>
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>