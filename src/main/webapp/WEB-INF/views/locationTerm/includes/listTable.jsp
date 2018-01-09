<%--
 - Table of location terms.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.location.msgs.location" var="locationBundle"/>
<fmt:setBundle basename="omis.locationterm.msgs.locationTerm" var="locationTermBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table class="listTable" id="locationTerms">
	<thead>
	<tr>
		<th class="operations"/>
		<th><fmt:message key="locationLabel" bundle="${locationBundle}"/></th>
		<th><fmt:message key="reasonLabel" bundle="${locationTermBundle}"/></th>
		<th class="datetime"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
		<th class="datetime"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
		<th class="datetime"><fmt:message key="dayCountLabel" bundle="${locationTermBundle}"/>
	</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>