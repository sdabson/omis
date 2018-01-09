<%--
  - Table of historical offense terms.
  -
  - Historical offense terms are inactive sentences.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="sentenceBundle" basename="omis.sentence.msgs.sentence"/>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<table class="listTable" id="historicalOffenseTerms">
	<thead>
		<tr>
			<th></th>
			<th><fmt:message key="offenseLabel" bundle="${convictionBundle}"/></th>
			<th><fmt:message key="termLabel" bundle="${offenseTermBundle}"/>
			<th><fmt:message key="pronouncementDateLabel" bundle="${sentenceBundle}"/>
			<th><fmt:message key="dateLabel" bundle="${convictionBundle}"/>
			<th><fmt:message key="changeOrderLabel" bundle="${sentenceBundle}"/>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listHistoricalTableBodyContent.jsp"/>
	</tbody>
</table>