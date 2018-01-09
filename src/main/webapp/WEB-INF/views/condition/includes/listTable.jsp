<%--
 - Author: Jonny Santy
 - Version: 0.1.0 (Jul 12, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.condition.msgs.condition">
<table id="conditions" class="listTable">
	<thead>
		<tr>
		<th><fmt:message key="conditionAgreementLabel"/></th>
		<th><fmt:message key="conditionSummaryTitleLabel"/></th>
		<th><fmt:message key="conditionSummaryCauseLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>