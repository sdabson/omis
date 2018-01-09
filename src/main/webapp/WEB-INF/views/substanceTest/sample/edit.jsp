<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.substance.msgs.substance">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.search.js"> </script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/search.js"> </script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
	<script type="text/javascript">
		/* <![CDATA[ */
			//offender id for use with action menu display
			var offender = ${offender.id};
		/* ]]> */
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/substanceTest/sample/scripts/jQuery/jquery.omis.substanceTestSample.js?VERSION=1"> </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/substanceTest/sample/scripts/substanceTestSample.js?VERSION=1"> </script>
	<title>
		<c:choose>
			<c:when test="${not empty substanceTestSample}">
				<fmt:message key="substanceTestSampleEditTitle">
				</fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="substanceTestSampleCreateTitle">
				</fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:otherwise>
		</c:choose>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/substanceTest/sample/substanceTestSampleActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty substanceTestSample}">
				<fmt:message key="substanceTestSampleEditTitle">
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="substanceTestSampleCreateTitle">
				</fmt:message>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>