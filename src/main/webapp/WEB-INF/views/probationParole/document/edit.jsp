<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/document/includes/documentTagResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/probationParole/document/scripts/probationParoleDocument.js?VERSION=1"></script>
		<title>
			<c:if test="${empty probationParoleDocumentAssociation}">
				<fmt:message key="createProbationParoleDocumentTitle" />
			</c:if>
			<c:if test="${not empty probationParoleDocumentAssociation}">
				<fmt:message key="editProbationParoleDocumentTitle" />
			</c:if>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/probationParole/document/probationParoleDocumentActionMenu.html?offender=${offender.id}" id="actionMenuLink" class="actionMenuItem"></a>
			<c:if test="${empty probationParoleDocumentAssociation}">
				<fmt:message key="createProbationParoleDocumentTitle" />
			</c:if>
			<c:if test="${not empty probationParoleDocumentAssociation}">
				<fmt:message key="editProbationParoleDocumentTitle" />
			</c:if>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</fmt:bundle>
</html>