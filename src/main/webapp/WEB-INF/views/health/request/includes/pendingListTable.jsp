<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 14, 2014)
 - Since: OMIS 3.0 
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
	<table id="pendingRequestsListTable" class="listTable">
		<thead>
			<tr>
			<th class="evenMoreOperations"></th>
			<th><fmt:message key="offenderLabel"/></th>
			<th class="asap"><fmt:message key="asapLabel"/></th>
			<th class="requestDate"><fmt:message key="requestDateLabel"/></th>
			<th><fmt:message key="requiredReferralTypeLabel"/></th>
			<th><fmt:message key="offenderUnitLabel"/></th>
			<th class="notes"><fmt:message key="noteLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="pendingListTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>
