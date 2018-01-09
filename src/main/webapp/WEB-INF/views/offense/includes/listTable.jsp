<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offense.msgs.offense">

<table id="bedPlacements" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="violationCodeLabel"/></th>
			<th><fmt:message key="nameLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>