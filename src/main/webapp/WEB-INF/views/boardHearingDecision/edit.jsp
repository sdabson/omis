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
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 23, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.boardhearingdecision.msgs.boardHearingDecision">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/boardHearingDecision/scripts/JQuery/jquery.omis.boardHearingDecision.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/boardHearingDecision/scripts/boardHearingDecision.js?VERSION=1"></script>
	<script type="text/javascript">
		var currentHearingDecisionNoteIndex = ${hearingDecisionNoteIndex};
	</script>
	<title>
		<c:if test="${empty boardHearingDecision}">
			<fmt:message key="createBoardHearingDecisionHeader" />
		</c:if>
		<c:if test="${not empty boardHearingDecision}">
			<fmt:message key="editBoardHearingDecisionHeader" />
		</c:if>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/boardHearingDecision/boardHearingDecisionActionMenu.html?boardHearing=${boardHearing.id}"></a>
		<c:if test="${empty boardHearingDecision}">
			<fmt:message key="createBoardHearingDecisionHeader" />
		</c:if>
		<c:if test="${not empty boardHearingDecision}">
			<fmt:message key="editBoardHearingDecisionHeader" />
		</c:if>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>