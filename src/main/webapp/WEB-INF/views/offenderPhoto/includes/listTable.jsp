<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Feb 25, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.media.msgs.media" var="mediaBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th class="smallPhoto"><fmt:message key="photoLabel" bundle="${mediaBundle}"/></th>
			<th class="date"><fmt:message key="photoDateLabel" bundle="${mediaBundle}"/></th>			
			<th><fmt:message key="updateUserAccountLabel" bundle="${auditBundle}"/></th>
			<th class="date"><fmt:message key="updateDateLabel" bundle="${auditBundle}"/></th>
			<th><fmt:message key="mugshotUseLabel" bundle="${mediaBundle}"/></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>