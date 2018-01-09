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
 - Screen to change supervisory organization.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.supervision.msgs.placementTerm">
<head>
	<title><fmt:message key="changeSupervisoryOrganizationHeader"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/supervision/style/links.css"/>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/supervision/scripts/changeSupervisoryOrganization.js"> </script>
  	<script type="text/javascript">
  		// <![CDATA[
  		var offender = ${offender.id};
  		// ]]>
  	</script>
</head>
<body>
	<h1><fmt:message key="changeSupervisoryOrganizationHeader"/></h1>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<ul class="toolbar">
		<sec:authorize access="hasRole('ADMIN') or hasRole('PLACEMENT_TERM_LIST')">
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/supervision/placementTerm/list.html?offender=${offender.id}">
				<span class="visibleLinkLabel"><fmt:message key="listPlacementTermsLink"/></span></a>
		</li>
		</sec:authorize>
	</ul>
	<jsp:include page="includes/changeSupervisoryOrganizationForm.jsp"/>
</body>
</fmt:bundle>
</html>