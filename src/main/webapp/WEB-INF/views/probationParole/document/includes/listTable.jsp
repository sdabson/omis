<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
	<table class="listTable">
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="dateLabel" /></th>
			<th><fmt:message key="categoryLabel" /></th>
			<th><fmt:message key="titleLabel" /></th>
			<th><fmt:message key="uploadByLabel" /></th>
		</tr>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp" />
		</tbody>
	</table>
</fmt:bundle>