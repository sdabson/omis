<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<table id="adaAccommodationListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<sec:authorize access="hasRole('ADA_ACCOMMODATION_VIEW') or hasRole('ADMIN')">
			<th><fmt:message key="disabilityLabel"/></th></sec:authorize>
			<th><fmt:message key="accommodationDescriptionLabel"/></th>
			<th><fmt:message key="accommodationCategoryLabel"/></th>
			<th class="date"><fmt:message key="approvalDateLabel"/></th>
			<th><fmt:message key="authorizationSourceLabel"/></th>
			<th class="date"><fmt:message key="expirationDateLabel"/></th>
			<th><fmt:message key="createdByLabel"/></th>
		</tr>
	</thead>
	<tbody id="accommodationListTableBody">	
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>