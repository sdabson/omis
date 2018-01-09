<%--
  - Offender contact telephone number edit table.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offendercontact.msgs.offenderContact" var="offenderContactBundle"/>
<table class="editTable" id="onlineAccountTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="onlineAccountActionMenuLink" href="${pageContext.request.contextPath}/offenderContact/onlineAccountsActionMenu.html?offender=${offender.id}"></a></th>
			<jsp:include page="../../contact/includes/onlineAccountFieldsHeader.jsp"/>
		</tr>
	</thead>
	<tbody id="onlineAccountsBody">
		<c:forEach var="onlineAccountItem" items="${onlineAccountItems}" varStatus="status">
			<c:set var="onlineAccountItem" value="${onlineAccountItem}" scope="request"/>
			<c:set var="onlineAccountItemIndex" value="${status.index}" scope="request"/>
			<jsp:include page="onlineAccountEditTableRow.jsp"/>
		</c:forEach>
	</tbody>
</table>