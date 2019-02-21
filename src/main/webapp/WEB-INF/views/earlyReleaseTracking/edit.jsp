<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/document/includes/documentTagResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/user/scripts/searchUserAccount.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/earlyReleaseTracking/scripts/includes/jquery.omis.earlyReleaseRequest.js?VERSION=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/earlyReleaseTracking/scripts/earlyReleaseRequest.js?VERSION=1"></script>
		<script type="text/javascript">
			var currentEarlyReleaseRequestNoteAssociationItemIndex = ${earlyReleaseRequestNoteAssociationItemIndex};
			var currentEarlyReleaseRequestDocumentAssociationItemIndex = ${earlyReleaseRequestDocumentAssociationItemIndex};
			var currentEarlyReleaseRequestInternalApprovalItemIndex = ${earlyReleaseRequestInternalApprovalItemIndex};
			var currentEarlyReleaseRequestExternalOppositionItemIndex = ${earlyReleaseRequestExternalOppositionItemIndex};
			var currentDocumentTagItemIndexes = [];
			<c:forEach items="${documentTagItemIndexes}" var="id">
				currentDocumentTagItemIndexes.push(parseInt("${id}"));
			</c:forEach>
		</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/earlyReleaseTracking/style/earlyReleaseTracking.css?VERSION=1" />
		<title>
			<c:if test="${empty earlyReleaseRequest}">
				<fmt:message key="create${earlyReleaseRequestCategory}RequestTitle" />
			</c:if>
			<c:if test="${not empty earlyReleaseRequest}">
				<fmt:message key="edit${earlyReleaseRequestCategory}RequestTitle" />
			</c:if>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a href="${pageContext.request.contextPath}/earlyReleaseTracking/earlyReleaseRequestActionMenu.html?offender=${offender.id}" id="actionMenuLink" class="actionMenuItem"></a>
			<c:if test="${empty earlyReleaseRequest}">
				<fmt:message key="create${earlyReleaseRequestCategory}RequestTitle" />
			</c:if>
			<c:if test="${not empty earlyReleaseRequest}">
				<fmt:message key="edit${earlyReleaseRequestCategory}RequestTitle" />
			</c:if>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</fmt:bundle>
</html>