<?xml version="1.0" encoding="UTF-8"?>
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
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<c:if test="${not empty contactSummary}">
		<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
	</c:if>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<script type="text/javascript">
		/* <![CDATA[ */
			//offender id for use with action menu display
			var offender = <c:choose><c:when test="${not empty offender}">${offender.id}</c:when><c:otherwise>null</c:otherwise></c:choose>;
		/* ]]> */
	</script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/listOffenderRelationships.js"> </script>
	<title>
		<fmt:message key="offenderRelationshipListHeader"/>
		<c:if test="${not empty offender}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</c:if>
	</title>
</head>
 <body>
 	<c:if test="${not empty offenderSummary}">
 		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
 	</c:if>
 	<c:if test="${not empty contactSummary}">
 		<jsp:include page="/WEB-INF/views/contact/includes/contactSummary.jsp"/>
 	</c:if>
 	<h1>
		<a class="actionMenuItem" id="offenderRelationshipListActionMenuLink" href="${pageContext.request.contextPath}/offenderRelationship/offenderRelationshipsListActionMenu.html?offender=${offender.id}&amp;relation=${relation.id}"></a><span class="visibleLinkLabel"/>
		<fmt:message key="offenderRelationshipListHeader"/>	
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>