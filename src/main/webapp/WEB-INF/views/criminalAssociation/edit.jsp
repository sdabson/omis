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
 - Author: Joel Norris
 - Author: Yidong Li
 - Author: Josh Divine
 - Version: 0.1.11 (Nov 28, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">
<head>
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE10"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/criminalAssociation/scripts/criminalAssociation.js"> </script>
<title>
	<c:choose>
		<c:when test="${not empty criminalAssociation}">
			<fmt:message key="associationEditTitle">
			</fmt:message>
		</c:when>
		<c:otherwise>
			<fmt:message key="associationCreateTitle">
			</fmt:message>
		</c:otherwise>
	</c:choose>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
</title>
</head>
<body>
	<c:if test="${not empty offenderSummary}">
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	</c:if>
	<sec:authorize access="hasRole('CRIMINAL_ASSOCIATION_CREATE') or hasRole('ADMIN')">
	<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/criminalAssociation/criminalAssociationActionMenu.html?offender=${offenderSummary.id}"><span class="visibleLinkLabel"/></a>

				<c:choose>
					<c:when test="${not empty criminalAssociation}">  
						<fmt:message key="associationEditTitle"></fmt:message>
					</c:when>	
					<c:otherwise>
						<fmt:message key="associationCreateTitle"></fmt:message>
					</c:otherwise>	
				</c:choose>
	</h1>
	</sec:authorize>
	<jsp:include page="/WEB-INF/views/criminalAssociation/includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>