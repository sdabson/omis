<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
<sec:authorize var="editCourtCaseCondition" access="hasRole('COURT_CASE_CONDITION_CREATE') or hasRole('COURT_CASE_CONDITION_EDIT') or hasRole('ADMIN')"/>
<form:form commandName="conditionSelectForm">
	<form:input type="hidden" path="conditionCategory" />
	<table id="selectConditionsTable" class="listTable">
		<thead>
			<tr>
				<th class="operations"><input type="checkbox" id="selectAll" /></th>
				<th><fmt:message key="conditionTitleLabel"/></th>
				<th><fmt:message key="conditionClauseLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<c:set var="conditionItems" value="${conditionSelectForm.conditionItems}" scope="request" />
			<jsp:include page="selectConditionsListTableBodyContent.jsp"/>
		</tbody>
	</table>
<c:if test="${editCourtCaseCondition}">
	<p class="buttons">
		<input type="submit" value="<fmt:message key="selectConditionsLabel"/>"/>
	</p>
</c:if>
</form:form>
</fmt:bundle>