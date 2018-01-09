<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.military.msgs.military">
		<head>
			<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/military/scripts/includes/jquery.omis.serviceTerm.js?VERSION=1"> </script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/military/scripts/serviceTerm.js?VERSION=1"> </script>
			<script type="text/javascript">
				/* <![CDATA[ */
					//service term note index used to track service term note items on the form 
					var currentServiceTermNoteItemIndex = ${serviceTermNoteItemIndex};
				/* ]]> */
			</script>
			<title>
				<c:choose>
					<c:when test="${not empty serviceTerm}">
						<fmt:message key="serviceTermEditTitle"/>
						<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="serviceTermCreateTitle"/>
						<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
					</c:otherwise>
				</c:choose>
			</title>
		</head>
		<body>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
			<h1>
				<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/military/militaryServiceTermActionMenu.html?offender=${offender.id}"></a>
				<c:choose>
					<c:when test="${not empty serviceTerm}">
						<fmt:message key="serviceTermEditTitle"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="serviceTermCreateTitle"/>
					</c:otherwise>
				</c:choose>
			</h1>
			<jsp:include page="includes/editForm.jsp"/>
		</body>
	</fmt:bundle>
</html>