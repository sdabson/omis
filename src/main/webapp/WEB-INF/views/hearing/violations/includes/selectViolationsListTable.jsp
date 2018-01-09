<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<c:choose>
	<c:when test="${goTo eq 'VIOLATIONS_LIST' or goTo eq 'HEARINGS_LIST' or goTo eq 'ADJUDICATE'}">
		<c:set var="action" value='${pageContext.request.contextPath}/hearing/violations/select.html?goToOption=${goTo}'/>
		<c:set var="method" value='POST' />
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${resolutionCategory eq 'FORMAL'}">
				<c:set var="action" value='${pageContext.request.contextPath}/hearing/create.html' />
				<c:set var="method" value='GET' />
			</c:when>
			<c:when test="${resolutionCategory eq 'INFORMAL' or resolutionCategory eq 'DISMISSED'}">
				<c:set var="action" value='${pageContext.request.contextPath}/hearing/resolution/create.html'/>
				<c:set var="method" value='GET' />
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>

<form:form commandName="violationsSelectForm" action="${action}" method="${method}">
<form:input type="hidden" path="offender" />
<form:input type="hidden" path="hearing" />
<form:input type="hidden" path="resolutionCategory" />
<form:input type="hidden" path="violationCategory" />
<form:errors path="violationSelectionItems" cssClass="selectionError" />
<table id="selectViolationSummariesTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"><input type="checkbox" id="selectAll" /></th>
			<th><fmt:message key="eventCategoryLabel"/></th>
			<th><fmt:message key="eventDateLabel"/></th>
			<th><fmt:message key="eventDetailsLabel"/></th>
			<th><fmt:message key="violationDetailsLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="violationSelectionItems" value="${violationsSelectForm.violationSelectionItems}" scope="request"/>
		<jsp:include page="selectViolationsListTableBodyContent.jsp"/>
	</tbody>
</table>

<p class="buttons">
	<input type="submit" value="<fmt:message key="selectViolations${resolutionCategory}ListHeader"/>"/>
</p>
</form:form>
</fmt:bundle>