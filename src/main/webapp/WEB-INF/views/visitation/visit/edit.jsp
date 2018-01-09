<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery.ptTimeSelect.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery.ptTimeSelect.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/jquery/jquery.omis.visit.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/visit.js"> </script>
		<script type="text/javascript">
			/* <![CDATA[ */
				//offender id for use with ajax functionality
				var offenderId = ${offender.id};
			/* ]]> */
		</script>
		<title>
			<c:choose>
				<c:when test="${not empty visit}">
					<fmt:message key="visitEditTitle"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="visitCreateTitle"></fmt:message>
				</c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a class="actionMenuItem" id="visitActionMenuLink" href="${pageContext.request.contextPath}/visitation/visit/visitActionMenu.html?offender=${offender.id}"></a>
			<c:choose>
				<c:when test="${not empty visit}">
					<fmt:message key="visitEditTitle"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="visitCreateTitle"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="includes/visitForm.jsp"/>
	</body>
</fmt:bundle>
</html>