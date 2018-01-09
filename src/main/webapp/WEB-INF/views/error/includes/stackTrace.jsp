<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.error.msgs.error">
	<h2><fmt:message key="stackTraceLabel"/></h2>
	<span class="exception"><c:out value="${pageContext.exception}"/></span>
	<ul class="stackTraceElements">
	<c:forEach var="stackTraceElement" items="${pageContext.exception.stackTrace}">
		<li class="stackTraceElement">
			<span class="stackTraceElementClassName"><c:out value="${stackTraceElement.className}"/></span>
			<span class="stackTraceElementMethodName"><c:out value="${stackTraceElement.methodName}"/></span>
			<span class="stackTraceElementFileName"><c:out value="${stackTraceElement.fileName}"/></span>
			<span class="stackTraceElementLineNumber"><c:out value="${stackTraceElement.lineNumber}"/></span>
		</li>
	</c:forEach>
	</ul>
	<c:if test="${not empty pageContext.exception.cause}">
		<h3><fmt:message key="rootCauseLabel"/></h3>
		<span class="exception"><c:out value="${pageContext.exception.cause}"/></span>
		<ul class="stackTraceElements">
		<c:forEach var="stackTraceElement" items="${pageContext.exception.cause.stackTrace}">
		<li class="stackTraceElement">
			<span class="stackTraceElementClassName"><c:out value="${stackTraceElement.className}"/></span>
			<span class="stackTraceElementMethodName"><c:out value="${stackTraceElement.methodName}"/></span>
			<span class="stackTraceElementFileName"><c:out value="${stackTraceElement.fileName}"/></span>
			<span class="stackTraceElementLineNumber"><c:out value="${stackTraceElement.lineNumber}"/></span>
		</li>
		</c:forEach>
		</ul>
	</c:if>
</fmt:bundle>