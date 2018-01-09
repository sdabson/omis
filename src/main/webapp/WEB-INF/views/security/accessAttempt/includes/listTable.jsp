<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.security.msgs.accessAttempt">
<table class="listTable" id="accessAttemptsTable">
	<thead>
		<tr>
		<th><fmt:message key="usernameHeader"/></th>
		<th><fmt:message key="dateHeader"/></th>
		<th><fmt:message key="remoteAddressHeader"/></th>
		<th><fmt:message key="remoteHostHeader"/></th>
		<th><fmt:message key="successHeader"/></th>
		<th><fmt:message key="userAgentHeader"/></th>
		</tr>
	</thead>
	<tbody id="accessAttempts">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>