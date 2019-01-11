<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.location.msgs.location" var="locationBundle"/>
<fmt:setBundle basename="omis.supervision.msgs.supervisoryOrganizationTerm" var="supervisoryOrganizationBundle"/>
<fmt:setBundle basename="omis.supervision.msgs.correctionalStatusTerm" var="correctionalStatusBundle"/>
<fmt:setBundle basename="omis.offender.msgs.offenderSearch" var="offenderSearchBundle"/>
<!-- 	Commented out code use modules not yet in production. -->
<fieldSet>
	<table id="offenderSearchResultsListTable" class="listTable">
		<thead>
			<tr>
				<th class="operations"/>
				<th class="photos"></th>
				<th><fmt:message key="nameLabel" bundle="${offenderSearchBundle}"/></th>				
				 <th class="date"><fmt:message key="dateOfBirthLabel" bundle="${offenderSearchBundle}"/></th>
				<th><fmt:message key="genderLabel" bundle="${offenderSearchBundle}"/></th>
				<sec:authorize access="hasRole('LOCATION_TERM_VIEW') or hasRole('ADMIN')">
				 	<th><fmt:message key="locationDetailTitle" bundle="${locationBundle}"/></th>
				 </sec:authorize>
<%-- 				<th><fmt:message key="supervisoryOrganizationLabel" bundle="${supervisoryOrganizationBundle}"/></th> --%>
				<sec:authorize access="hasRole('CORRECTIONAL_STATUS_TERM_VIEW') or hasRole('ADMIN')">
					<th><fmt:message key="correctionalStatusLabel" bundle="${correctionalStatusBundle}"/></th>
				</sec:authorize>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty searchSummaries}">
				<jsp:include page="listTableBodyContent.jsp"/>
			</c:if>	
		</tbody>
	</table>
</fieldSet>