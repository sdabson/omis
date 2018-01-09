<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.caseload.msgs.caseloadSearch" var="caseloadSearchBundle"/>
<fieldSet>
	<table id="caseloadSearchResultsListTable" class="listTable">
		<thead>
			<tr>
				<th class="operations"/>
				<th><fmt:message key="caseWorkerLabel" bundle="${caseloadSearchBundle}"/></th>
<%-- 				<th><fmt:message key="caseWorkCategoryLabel" bundle="${caseloadSearchBundle}"/></th> --%>
<%-- 				<th class="date"><fmt:message key="startEndDatesLabel" bundle="${caseloadSearchBundle}"/></th> --%>
				<th><fmt:message key="caseloadLabel" bundle="${caseloadSearchBundle}"/></th>
			</tr>
		</thead>
		<tbody>
				<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fieldSet>