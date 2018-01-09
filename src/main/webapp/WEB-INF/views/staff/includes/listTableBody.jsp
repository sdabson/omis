<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffSearchBundle"/>
<fieldSet>
	<table id="staffSearchResultsListTable" class="listTable">
		<thead>
			<tr>
				<th class="operations"/>
				<th><fmt:message key="nameLabel" bundle="${staffSearchBundle}"/></th>
				<th><fmt:message key="locationLabel" bundle="${staffSearchBundle}"/></th>
				<th><fmt:message key="divisionLabel" bundle="${staffSearchBundle}"/></th>
				<th><fmt:message key="jobTitleLabel" bundle="${staffSearchBundle}"/></th>
				<th><fmt:message key="phoneNumberLabel" bundle="${staffSearchBundle}"/></th>
				<th><fmt:message key="emailLabel" bundle="${staffSearchBundle}"/></th>
			</tr>
		</thead>		
		<tbody>
			<c:if test="${not empty searchSummaries}">
				<jsp:include page="listTableBodyContent.jsp"/>
			</c:if>
		</tbody>
	</table>
</fieldSet>