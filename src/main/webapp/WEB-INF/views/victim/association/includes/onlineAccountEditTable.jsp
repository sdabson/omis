<%--
  - Victim contact telephone number edit table.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<table class="editTable" id="onlineAccountTable">
	<thead>
		<tr>
			<th>
				<a class="actionMenuItem" id="onlineAccountActionMenuLink" href="${pageContext.request.contextPath}/victim/contact/onlineAccountActionMenu.html?itemIndex=${telephoneNumberItemIndex}"></a>
			</th>
			<th><fmt:message key="victimContactOnlineAccountNameLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimContactOnlineAccountPrimaryLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimContactOnlineAccountHostLabel" bundle="${victimBundle}"/></th>
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