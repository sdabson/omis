<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<table class="listTable">
	<thead>
	<tr>
		<th class="operations"/>
		<th><fmt:message key="docketLabel"/></th>
		<th><fmt:message key="courtLabel"/></th>
		<th><fmt:message key="judgeLabel"/></th>
		<th><fmt:message key="youthCaseLabel"/></th>
		<th class="date"><fmt:message key="pronouncementDateLabel"/></th>
		<th><fmt:message key="dismissedLabel"/></th>
	</tr>
	</thead>
	<tbody id="courtCases">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>