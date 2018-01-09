<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/separationNeed/scripts/jquery/jquery.omis.separationNeed.js"> </script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/separationNeed/scripts/separationNeed.js"> </script>
	    <script type="text/javascript">
			/* <![CDATA[ */
				//separation need note index used to track separation need note items on the form
				var currentSeparationNeedNoteItemIndex = ${separationNeedNoteItemIndex};
			/* ]]> */
		</script>
	<title>
		<c:choose>
			<c:when test="${not empty separationNeed}">
				<fmt:message key="separationNeedEditTitle"></fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="separationNeedCreateTitle"></fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:otherwise>
		</c:choose>
	</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/separationNeed/separationNeedActionMenu.html?offender=${offender.id}"></a>
			<c:choose>
				<c:when test="${not empty separationNeed}">
					<fmt:message key="separationNeedEditTitle"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="separationNeedCreateTitle"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</fmt:bundle>
</html>