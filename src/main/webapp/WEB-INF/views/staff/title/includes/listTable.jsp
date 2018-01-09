<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="nameLabel" bundle="${staffBundle}"/>
			<th><fmt:message key="sortOrderLabel" bundle="${staffBundle}"/>
			<th><fmt:message key="validLabel" bundle="${staffBundle}"/>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>