<%--
  - Grievances list table.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="grievanceNumberLabel" bundle="${grievanceBundle}"/></th>
			<c:if test="${empty offender}">
				<th><fmt:message key="offenderLabel" bundle="${offenderBundle}"/></th>
			</c:if>
			<th><fmt:message key="grievanceOpenedDateLabel" bundle="${grievanceBundle}"/></th>
			<th><fmt:message key="grievanceSubjectLabel" bundle="${grievanceBundle}"/></th>
			<th><fmt:message key="grievanceStatusLabel" bundle="${grievanceBundle}"/></th>
			<th><fmt:message key="grievanceDescriptionLabel" bundle="${grievanceBundle}"/></th>
		</tr>
	</thead>
	<tbody id="grievances">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>