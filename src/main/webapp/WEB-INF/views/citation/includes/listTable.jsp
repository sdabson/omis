<%--
 - Author: Trevor Isles
 - Version: 0.1.0 (Aug 22, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.citation.msgs.citation">
<table id="citations" class="listTable">
	<thead>
		<tr>
		<th class="operations"/>
		<th><fmt:message key="offenseLabel"/></th>
		<th><fmt:message key="citationCountsLabel"/></th>
		<th><fmt:message key="monthLabel"/></th>
		<th><fmt:message key="dayLabel"/></th>
		<th><fmt:message key="yearLabel"/></th>
		<th><fmt:message key="stateLabel"/></th>
		<th><fmt:message key="cityLabel"/></th>
		<th><fmt:message key="dispositionLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>