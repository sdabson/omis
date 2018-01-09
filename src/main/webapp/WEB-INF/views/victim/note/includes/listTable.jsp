<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"> </th>
			<th><fmt:message key="victimAssociationLabel" bundle="${victimBundle}"/>
			<th><fmt:message key="victimNoteDateLabel" bundle="${victimBundle}"/>
			<th><fmt:message key="victimNoteValueLabel" bundle="${victimBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>