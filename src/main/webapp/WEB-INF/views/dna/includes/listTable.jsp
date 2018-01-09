<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.dna.msgs.dna">
<table id="listingTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="dnaSampleDateLabel"/></th>
			<th><fmt:message key="dnaSampleTimeLabel"/></th>
			<th><fmt:message key="dnaCollectionEmployeeLabel"/></th>
			<th><fmt:message key="dnaWitnessLabel"/></th>
			<th><fmt:message key="dnaLocationLabel"/></th>
			<th><fmt:message key="dnaCommentsLabel"/></th>
	</thead>
	<tbody id="dnas">
 		<jsp:include page="listTablebodyContent.jsp"/> 
	</tbody>
</table>
</fmt:bundle>