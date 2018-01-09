<%--
 - Table to list user accounts. 
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.user.msgs.userAccount">
<table class="listTable">
	<colgroup>
		<col class="operations">
	</colgroup>
	<thead>
		<tr>
		<th/>
		<th><fmt:message key="usernameHeader"/></th>
		<th><fmt:message key="userHeader"/></th>
		<%-- <th><fmt:message key="enabledHeader"/></th> --%>
		</tr>
	</thead>
	<tbody id="userAccounts" class="listTable">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>