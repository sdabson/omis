<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.address.msgs.address" var="addressBundle"/>
<fmt:setBundle basename="omis.residence.msgs.residence" var="residenceBundle"/>
<fieldSet>
	<table id="residenceSearchResultsListTable" class="listTable">
		<thead>
			<tr>
				<th class="operations"/>
				<th><fmt:message key="personNameLabel" bundle="${residenceBundle}"/></th>
				<th><fmt:message key="addressLabel" bundle="${addressBundle}"/></th>
				<th><fmt:message key="termDatesLabel" bundle="${residenceBundle}"/></th>
				<th><fmt:message key="residenceConfirmationLabel" bundle="${residenceBundle}"/></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty searchSummaries}">
				<jsp:include page="searchResultsListTableBodyContent.jsp"/>
			</c:if>
		</tbody>
	</table>
</fieldSet>