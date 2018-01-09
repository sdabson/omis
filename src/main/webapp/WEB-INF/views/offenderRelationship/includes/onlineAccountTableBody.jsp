<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.contact.msgs.contact">
<table id="onlineAccountsTable" class="editTable">
<thead>
	<jsp:include page="../../contact/includes/onlineAccountFieldsHeader.jsp"/>	
</thead>
	<tbody id="onlineAccounts">
	<c:forEach var="onlineAccountContactItem" items="${createRelationshipsForm.onlineAccountItems}" varStatus="status">
		<c:set var="onlineAccountIndex" value="${status.index}" scope="request"/>			
		<jsp:include page="onlineAccountTableRow.jsp"/>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>