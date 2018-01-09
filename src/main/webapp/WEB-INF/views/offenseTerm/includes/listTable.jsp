<%--
  - Table of offense items.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<fmt:setBundle basename="omis.courtcase.msgs.courtCase" var="courtCaseBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="docketValueLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="courtNameLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="judgeLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="dismissedLabel" bundle="${courtCaseBundle}"/>
		</tr>
	</thead>
	<tbody id="courtCases">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>