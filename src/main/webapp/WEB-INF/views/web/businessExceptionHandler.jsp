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
  - Screen to handle business exceptions.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle var="bundle" basename="${messageBundle}"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<title>Business Violation</title>
</head>
<body>
	<div id="businessViolationMessage">
		<p class="businessViolationTitle">Business Violation</p>
		<p class="businessMessageKey"><fmt:message key="${messageKey}" bundle="${bundle}"/></p>
		<button type="button" onclick="window.history.back()"><fmt:message key="backLabel" bundle="${commonBundle}"/></button>
	</div>
	<sec:authorize access="hasRole('APP_DEV')">
		<c:if test="${not empty businessException}">
			<fmt:setBundle basename="omis.error.msgs.error" var="errorBundle"/>
			<div>
			<h2><fmt:message key="stackTraceLabel"  bundle="${errorBundle}"/></h2>
			<span class="exception"><c:out value="${businessException}"/></span>
			<ul class="stackTraceElements">
			<c:forEach var="stackTraceElement" items="${businessException.stackTrace}">
			<li class="stackTraceElement">
				<span class="stackTraceElementClassName"><c:out value="${stackTraceElement.className}"/></span>
				<span class="stackTraceElementMethodName"><c:out value="${stackTraceElement.methodName}"/></span>
				<span class="stackTraceElementFileName"><c:out value="${stackTraceElement.fileName}"/></span>
				<span class="stackTraceElementLineNumber"><c:out value="${stackTraceElement.lineNumber}"/></span>
			</li>
			</c:forEach>
			</ul>
			<c:if test="${not empty businessException.cause}">
				<h3><fmt:message key="rootCauseLabel" bundle="${errorBundle}"/></h3>
				<span class="exception"><c:out value="${businessException.cause}"/></span>
				<ul class="stackTraceElements">
				<c:forEach var="stackTraceElement" items="${businessException.cause.stackTrace}">
					<li class="stackTraceElement">
					<span class="stackTraceElementClassName"><c:out value="${stackTraceElement.className}"/></span>
					<span class="stackTraceElementMethodName"><c:out value="${stackTraceElement.methodName}"/></span>
					<span class="stackTraceElementFileName"><c:out value="${stackTraceElement.fileName}"/></span>
					<span class="stackTraceElementLineNumber"><c:out value="${stackTraceElement.lineNumber}"/></span>
					</li>
				</c:forEach>
				</ul>
			</c:if>
			</div>
		</c:if>
	</sec:authorize>
</body>
</html>