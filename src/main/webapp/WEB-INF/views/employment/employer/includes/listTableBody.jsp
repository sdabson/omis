<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.employment.msgs.employment" var="employerBundle"/>
<fieldSet>
	<table id="employerSearchResultsListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="employerNameLabel" bundle="${employerBundle}"/></th>
			<th><fmt:message key="AddressNameLabel" bundle="${employerBundle}"/></th>
			<th><fmt:message key="cityLabel" bundle="${employerBundle}"/></th>
			<th><fmt:message key="phoneLabel" bundle="${employerBundle}"/></th>
			<th><fmt:message key="countLabel" bundle="${employerBundle}"/></th>
		</tr>
	</thead>	
	<tbody>
		<c:if test="${not empty searchSummaries}">
			<jsp:include page="listTableBodyContent.jsp"/>
		</c:if>
	</tbody>
	</table>		
</fieldSet>