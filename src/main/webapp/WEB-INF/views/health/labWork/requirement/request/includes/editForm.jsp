<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form commandName="requestLabWorkRequirementForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="requestLabWorkRequirementLabel" bundle="${healthBundle}"/></legend>
		<a id="requestLabWorkRequirementLink" class="createLink" href="${pageContext.request.contextPath}/health/labWork/requirement/request/addRequestItem.html?facility=${facility.id}&operation=CREATE" title="<fmt:message key='requestLabWorkRequirementLink' bundle='${healthBundle}'/>"><span class="visibleLinkLabel"><fmt:message key="requestLabWorkRequirementLink" bundle="${healthBundle}"/></span></a>
		<table class="editTable">
			<thead>
				<tr>
					<th class="operations"></th>
					<th><fmt:message key="labWorkSampleOrderedByLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkSampleOrderedDateLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkCategoryLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkSampleDateLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkSampleLabLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkResultsLabLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkSampleRestrictionsLabel" bundle="${healthBundle}"/></th>
					<th><fmt:message key="labWorkSchedulingNotesLabel" bundle="${healthBundle}"/></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="labWorkRequestBody">
				<c:forEach var="item" items="${requestLabWorkRequirementForm.labWorkRequirementRequestItems}" varStatus="status">
					<c:set var="item" value="${item}" scope="request"/>
					<c:set var="currentIndex" value="${status.index}" scope="request"/>
					<jsp:include page="requestTableRow.jsp"/>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>