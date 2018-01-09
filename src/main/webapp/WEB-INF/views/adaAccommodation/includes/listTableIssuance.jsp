<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<table id="accommodationIssuancesListTable" class="listTable">
	<thead>
		<tr id="issuance">			
			<th class="operations">
			<th class="datetime"><fmt:message key="dateTimeIssuedLabel"/></th>
			<th><fmt:message key="accommodationIssuanceDescriptionLabel"/></th>
			<th><fmt:message key="accommodationIssuanceIssuerLabel"/></th>
		</tr>
	</thead>
	<tbody>	
		<jsp:include page="listTableBodyContentIssuance.jsp"/>
	</tbody>	
</table>
</fmt:bundle>