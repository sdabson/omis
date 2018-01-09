<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offenderrelationship.msgs.createOffenderRelationship">
<head>
	<title>
		<fmt:message key="offenderRelationshipsCreateTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/form.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery-ui.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/caseload/style/form.css"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/personFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/contact/includes/poBoxFieldsResources.jsp"/>
<%-- 	<jsp:include page="/WEB-INF/views/family/includes/familyAssociationFieldsResources.jsp"/> --%>
<%-- 	<jsp:include page="/WEB-INF/views/victim/includes/victimAssociationFieldsResources.jsp"/> --%>
<%-- 	<jsp:include page="/WEB-INF/views/visitation/includes/visitationAssociationFieldsResources.jsp"/> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/JQuery/create/jquery.omis.createOffenderRelationshipCreateTelephoneNumber.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/JQuery/create/jquery.omis.createOffenderRelationshipCreateEmail.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/createOffenderRelationships.js?VERSION=1"></script>
	<script type="text/javascript">
		var offender = ${offender.id};
		var offenderRelationshipTelephoneNumberIndex= ${offenderRelationshipTelephoneNumberIndex};
		var offenderRelationshipOnlineAccountIndex= ${offenderRelationshipOnlineAccountIndex};
		var createFamilyMemberStatus= ${createFamilyMemberStatus};
		var createVictimStatus= ${createVictimStatus};
		var createVisitorStatus= ${createVisitorStatus};
		var newRelation = ${newRelation};
	</script>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<h1>
		<a class="actionMenuItem" id="offenderRelationshipListActionMenuLink" href="${pageContext.request.contextPath}/offenderRelationship/offenderRelationListActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="offenderRelationshipsCreateTitle"/>
	</h1>
	<jsp:include page="/WEB-INF/views/offenderRelationship/includes/createForm.jsp"/>
</body>
</fmt:bundle>
</html>