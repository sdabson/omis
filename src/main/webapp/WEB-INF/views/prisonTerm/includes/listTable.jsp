<%--
 - Table for prison term.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.prisonterm.msgs.prisonTerm">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th class="date"><fmt:message key="prisonTermActionDateLabel"/></th>
			<th class="date"><fmt:message key="prisonTermParoleEligibilityDateLabel"/></th>
			<th class="date"><fmt:message key="prisonTermProjectedDischargeDateLabel"/></th>
			<th><fmt:message key="prisonTermStatusLabel"/></th>
		</tr>
	</thead>
	<tbody id="prisonTerms">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>