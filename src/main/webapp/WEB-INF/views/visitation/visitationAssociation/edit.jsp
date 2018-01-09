<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/personFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/jquery/jquery.omis.visitationAssociation.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/visitationAssociation.js"> </script>
		<title>
			<c:choose>
				<c:when test="${not empty visitationAssociation}">
					<fmt:message key="visitationAssociationEditTitle"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="visitationAssociationCreateTitle"></fmt:message>
				</c:otherwise>
			</c:choose>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
	</head>
	<body>
		<c:if test="${not empty offender}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:if>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/visitation/visitationAssociationActionMenu.html?offender=${offender.id}"></a>
			<c:choose>
				<c:when test="${not empty visitationAssociation}">
					<fmt:message key="visitationAssociationEditTitle"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="visitationAssociationCreateTitle"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="../includes/editForm.jsp"/>
	</body>
</fmt:bundle>