<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
<table class="listTable">
	<thead>
		<tr>
		<th class="operations"/>
		<th><fmt:message key="offenderLabel"/></th>
		<th><fmt:message key="asapLabel"/></th>
		<th class="date"><fmt:message key="requestDateLabel"/></th>
		<th><fmt:message key="requiredReferralTypeLabel"/></th>
		<th><fmt:message key="healthRequestStatusLabel"/></th>
		<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>