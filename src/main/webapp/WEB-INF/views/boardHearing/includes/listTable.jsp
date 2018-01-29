<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
	<table class="listTable">
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="dateLabel" /></th>
			<th><fmt:message key="statusLabel" /></th>
			<th><fmt:message key="appearanceTypeLabel" /></th>
			<th><fmt:message key="locationLabel"/></th>
			<th><fmt:message key="analystLabel"/></th>
			<th><fmt:message key="decisionLabel"/></th>
		</tr>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp" />
		</tbody>
	</table>
</fmt:bundle>