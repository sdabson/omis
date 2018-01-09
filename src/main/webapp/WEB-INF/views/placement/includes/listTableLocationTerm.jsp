<%--
 - Supervisory organization associated location terms table.
 -
 - Author: Sheronda Vaughn
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.placement.msgs.placement">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table id="locationTermsListTable" class="listTable">
	<thead>
		<tr id="locationTerm">			
			<th class="operations">
			<th><fmt:message key="locationLabel"/></th>
			<th class="datetime"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
			<th class="datetime"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
			<th class="date"><fmt:message key="locationTermDayCountLabel"/></th>
		</tr>
	</thead>
	<tbody>	
		<jsp:include page="locationTermSummaryItem.jsp"/>
	</tbody>	
</table>
</fmt:bundle>