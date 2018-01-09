<%--
 - Author: Jonny Santy
 - Author: Annie Jacques
 - Version: 0.1.1 (Aug 30, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<table id="agreements" class="listTable">
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="causeLabel" /></th>
			<th><fmt:message key="agreementTypeLabel"/></th>
			<th><fmt:message key="dateRangeLabel" /></th>
		</tr>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp" />
		</tbody>
	</table>
</fmt:bundle>