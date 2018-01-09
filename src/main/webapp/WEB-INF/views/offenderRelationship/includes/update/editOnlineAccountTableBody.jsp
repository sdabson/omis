<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.contact.msgs.contact">
<table id="onlineAccountsTable" class="editTable">
<thead>
	<tr>
		<th>
			<a class="actionMenuItem" id="onlineAccountsEditActionMenuLink" href="${pageContext.request.contextPath}/offenderRelationship/update/onlineAccountContactEditActionMenu.html?offender=${offender.id}"></a>
		</th>
		<jsp:include page="../../../contact/includes/onlineAccountFieldsHeader.jsp"/>
	</tr>	
</thead>
	<tbody id="onlineAccountsTable">
	<c:forEach var="onlineAccountContactItem" items="${onlineAccountContactItems}" varStatus="status">
		<c:set var="onlineAccountIndex" value="${status.index}" scope="request"/>	
		<c:set var="onlineAccountContactItem" value="${onlineAccountContactItem}" scope="request"/>	
		<jsp:include page="editOnlineAccountTableRow.jsp"/>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>