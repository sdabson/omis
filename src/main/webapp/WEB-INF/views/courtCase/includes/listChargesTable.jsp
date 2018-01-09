<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Aug 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<table class="listTable">
	<thead>
	<tr>
		<th class="operations"/>
		<th><fmt:message key="courtLabel"/></th>
		<th><fmt:message key="docketValueLabel"/></th>
		<th><fmt:message key="chargeOffenseLabel"/></th>
		<th><fmt:message key="chargeCountsLabel"/></th>
		<th class="date"><fmt:message key="chargeDateLabel"/></th>
		<th class="date"><fmt:message key="chargeFileDateLabel"/></th>
	</tr>
	</thead>
	<tbody id="charges">
		<jsp:include page="listChargesTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>