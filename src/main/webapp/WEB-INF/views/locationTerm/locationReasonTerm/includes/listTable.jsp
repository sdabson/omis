<%--
 - List table of location reason terms.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.locationterm.msgs.locationTerm" var="locationTermBundle"/>
<fmt:setBundle basename="omis.locationterm.msgs.locationReason" var="locationReasonBundle"/>
<table class="listTable" id="locationReasonTerms">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="locationTermLabel" bundle="${locationTermBundle}"/></th>
			<th><fmt:message key="reasonLabel" bundle="${locationReasonBundle}"/></th>
			<th class="datetime"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
			<th class="datetime"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>