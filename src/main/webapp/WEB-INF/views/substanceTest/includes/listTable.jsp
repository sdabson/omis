<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.substance.msgs.substance">
<table id="substance" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th class="date"><fmt:message key="sampleDateLabel"/></th>
			<th><fmt:message key="sampleCollectionMethodLabel"/></th>
			<th><fmt:message key="substanceTestResultLabel"/></th>
			<th class="date"><fmt:message key="substanceTestDateLabel"/></th>
			<th><fmt:message key="substanceTestReasonLabel"/></th>
			<th><fmt:message key="verificationLabLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>