<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
	<table id="agreements" class="listTable">
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="agreementTypeLabel"/></th>
			<th><fmt:message key="dateRangeLabel" /></th>
		</tr>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp" />
		</tbody>
	</table>
</fmt:bundle>