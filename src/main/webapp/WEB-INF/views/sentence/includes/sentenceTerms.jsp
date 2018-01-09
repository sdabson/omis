<%--
 - Displays sentence terms (prison, probation, deferred).
 -
 - Snippet requires the term day, month and year for prison, probation and
 - deferred terms and indicators of whether to show each term.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="sentenceBundle" basename="omis.sentence.msgs.sentence"/>
<c:set var="firstTerm" value="${true}"/>
<c:if test="${showPrisonTerm}">
	<c:set var="firstTerm" value="${false}"/>
	<fmt:message key="sentencePrisonTermLabel" bundle="${sentenceBundle}">
		<fmt:param>${prisonYears}</fmt:param>
		<fmt:param>${prisonMonths}</fmt:param>
		<fmt:param>${prisonDays}</fmt:param>
	</fmt:message>
</c:if>
<c:if test="${showProbationTerm}">
	<c:if test="${not firstTerm}"><fmt:message key="termDeliminator" bundle="${sentenceBundle}"/></c:if>
	<fmt:message key="sentenceProbationTermLabel" bundle="${sentenceBundle}">
		<fmt:param>${probationYears}</fmt:param>
		<fmt:param>${probationMonths}</fmt:param>
		<fmt:param>${probationDays}</fmt:param>
	</fmt:message>
	<c:set var="firstTerm" value="${false}"/>
</c:if>
<c:if test="${showDeferredTerm}">
	<c:if test="${not firstTerm}"><fmt:message key="termDeliminator" bundle="${sentenceBundle}"/></c:if>
	<fmt:message key="sentenceDeferredTermLabel" bundle="${sentenceBundle}">
		<fmt:param>${deferredYears}</fmt:param>
		<fmt:param>${deferredMonths}</fmt:param>
		<fmt:param>${deferredDays}</fmt:param>
	</fmt:message>
</c:if>