<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.family.msgs.family">
	<table id="familyAssociations" class="listTable">
		<thead>
			<tr>
				<th></th>
				<th><fmt:message key="familyNameLabel"/></th>
				<th><fmt:message key="relationshipLabel"/></th>
				<th><fmt:message key="familyAddressLabel"/></th>
				<th><fmt:message key="phoneNumberLabel"/></th>
				<th><fmt:message key="emergencyContactLabel"/></th>
				<th><fmt:message key="familyDependantLabel"/></th>
				<th><fmt:message key="familyCohabitateLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>
	