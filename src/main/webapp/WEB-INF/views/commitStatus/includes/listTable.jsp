<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.commitstatus.msgs.commitStatus">
	<table id="commitStatuses" class="listTable">
		<thead>
			<tr>
				<th></th>
				<th><fmt:message key="startDateLabel"/></th>
				<th><fmt:message key="endDateLabel"/></th>
				<th><fmt:message key="statusLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>
	