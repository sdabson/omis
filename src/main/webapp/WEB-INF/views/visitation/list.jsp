<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.visitation.msgs.visitation">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<script type="text/javascript">
		/* <![CDATA[ */
			//offender id for use with action menu display
			var offender = ${offender.id};
		/* ]]> */
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/visitation/style/links.css?VERSION=1"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/visitation/style/visitation.css?VERSION=1"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/jquery/jquery.omis.visitationAssociations.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/visitation/scripts/visitationAssociations.js?VERSION=1"></script>
	<title>
		<fmt:message key="visitationListHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<c:if test="${not empty offender}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	
	<h1>
		<a class="actionMenuItem" id="visitsActionMenuLink" href="${pageContext.request.contextPath}/visitation/visit/visitsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="visitListHeader"/>
	</h1>
	<jsp:include page="includes/visitorLogDateForm.jsp"/>
	<div class="multiListTableContainer">
		<jsp:include page="includes/logListTable.jsp"/>
	</div>
	<h1>
		<a class="actionMenuItem" id="visitationAssociationsActionMenuLink" href="${pageContext.request.contextPath}/visitation/visitationAssociationsActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="offenderVisitorHeading"/>
	</h1>
	<div class="multiListTableContainer" id="visitationAssociationListContainer">
		<jsp:include page="includes/listTable.jsp"/>
	</div>
	<c:choose>
		<c:when test="${errorsPresent}">
			<c:set var="visitorCheckInDisplayClass" value=""/>
		</c:when>
		<c:otherwise>
			<c:set var="visitorCheckInDisplayClass" value="hidden"/>
		</c:otherwise>
	</c:choose>
	<c:set var="visitMethods" value="${visitMethods}" scope="request"/>
	<div id="visitorCheckInFormContainer" class="modalContainer ${visitorCheckInDisplayClass}">
		<div class="modalDialogue foregroundUltraLight">
			<jsp:include page="includes/visitorCheckInForm.jsp"/>
		</div>
	</div>
</body>
</fmt:bundle>
</html>