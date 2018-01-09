<%--
 - Table for STG activities.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="stgReportedByLabel"/></th>
			<th class="date"><fmt:message key="stgReportDateLabel"/></th>
			<th><fmt:message key="stgSummaryLabel"/></th>
			<th><fmt:message key="stgInvolvedOffendersLabel"/></th>
		</tr>
	</thead>
	<tbody id="activities">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>