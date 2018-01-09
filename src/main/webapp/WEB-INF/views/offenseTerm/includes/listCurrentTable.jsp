<%--
  - Table of sentence items.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<fmt:setBundle var="courtCaseBundle" basename="omis.courtcase.msgs.courtCase"/>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="courtCaseLabel" bundle="${courtCaseBundle}"/></th>
			<th><fmt:message key="offenseLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="termLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="countsLabel" bundle="${convictionBundle}"/></th>
			<th><fmt:message key="legalDispositionCategoryLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="pronouncementDateLabel" bundle="${courtCaseBundle}"/></th>
		</tr>
	</thead>
	<tbody id="sentences">
		<jsp:include page="listCurrentTableBodyContent.jsp"/>
	</tbody>
</table>