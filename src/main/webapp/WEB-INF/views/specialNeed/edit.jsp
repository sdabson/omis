<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
	<head>
	<title>
		<c:choose>
			<c:when test="${not empty specialNeed}">
				<fmt:message key="specialNeedEditTitle">
					<fmt:param>
						${classification.name}
					</fmt:param>
				</fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="newSpecialNeedLabel">
					<fmt:param>
						${classification.name}
					</fmt:param>
				</fmt:message>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:otherwise>
		</c:choose>
	</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
		<jsp:include page="/WEB-INF/views/document/includes/documentTagResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/specialNeed/style/specialNeed.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/specialNeed/scripts/jquery/jquery.omis.specialNeeds.js?VERSION=1"> </script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/specialNeed/scripts/specialNeed.js?VERSION=2"> </script>
		<script type="text/javascript">
		/* <![CDATA[ */
		   var currentNoteIndex = ${currentNoteIndex};       
		 /* ]]> */
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/specialNeed/specialNeedActionMenu.html?offender=${offender.id}"></a>
			<c:choose>
				<c:when test="${not empty specialNeed}">
					<fmt:message key="specialNeedEditTitle">
						<fmt:param>
							${classification.name}
						</fmt:param>
					</fmt:message>
				</c:when>
				<c:otherwise>
				<fmt:message key="newSpecialNeedLabel">
					<fmt:param>
						${classification.name}
					</fmt:param>
				</fmt:message>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</fmt:bundle>
</html>
