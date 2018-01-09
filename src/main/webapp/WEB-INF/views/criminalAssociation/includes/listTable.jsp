<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">

<table id="associations" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th class="date"><fmt:message key="associationStartDateLabel"/></th>
			<th class="date"><fmt:message key="associationEndDateLabel"/></th>
			<th><fmt:message key="associateNameLabel"/></th>
			<th><fmt:message key="criminalAssociationCategoryLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>
