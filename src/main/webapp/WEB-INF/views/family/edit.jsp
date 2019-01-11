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
<%--
 - Author: Ryan Johns
 - Author: Yidong Li
 - Author: Josh Divine
 - Version: 0.1.21 (Nov 28, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.family.msgs.family">
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty familyAssociation}">  
					<fmt:message key="updateFamilyAssociationLabel"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createFamilyAssociationLabel"/>
				</c:otherwise>	
			</c:choose>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/address/includes/addressFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/contact/includes/poBoxFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/personFieldsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/family/scripts/family.js"></script>
  		<script type="text/javascript">
  			var offenderRelationshipNoteItemIndex= ${offenderRelationshipNoteItemIndex};
  			var familyAssociationTelephoneNumberIndex= ${familyAssociationTelephoneNumberIndex};
  			var familyAssociationOnlineAccountIndex= ${familyAssociationOnlineAccountIndex};
			var existingFamilyMember = ${existingFamilyMember};
	  	</script>
	</head>
	<body>
		<c:if test="${not empty offenderSummary}">
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		</c:if>
		<h1>
			<a class="actionMenuItem" id="familyEditActionMenuLink" href="${pageContext.request.contextPath}/family/familyAssociationActionMenu.html?offender=${offenderSummary.id}"></a><span class="visibleLinkLabel"/>
			<c:choose>
				<c:when test="${not createFlag}">  
					<fmt:message key="updateFamilyAssociationLabel"/>
				</c:when>	
				<c:otherwise>
					<fmt:message key="createFamilyAssociationLabel"/>
				</c:otherwise>	
			</c:choose>
		</h1>
		<jsp:include page="/WEB-INF/views/family/includes/editForm.jsp"/>
	</body>
	</fmt:bundle>
</html>