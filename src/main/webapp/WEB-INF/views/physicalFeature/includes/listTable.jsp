<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
<table id="offenderPhysicalFeatureTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="featureClassificationLabel"/></th>
			<th><fmt:message key="physicalFeatureNameLabel"/></th>
			<th><fmt:message key="descriptionLabel"/></th>
			<th class="date"><fmt:message key="originationDateLabel"/></th>
		</tr>
	</thead>
	<tbody id="offenderPhysicalFeatureTable">
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>