<%--
 - Author: Jason Nelson
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.tierdesignation.msgs.tierDesignation">
<table class="listTable">
	<thead>
		<tr>
		<th class="operations"/>
		<th><fmt:message key="levelLabel"/></th>
		<th><fmt:message key="sourceLabel"/></th>
		<th><fmt:message key="changeReasonLabel"/></th>
		<th class="date"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
		<th class="date"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
		</tr>
	</thead>
	<tbody id="tierDesignations">
 		<jsp:include page="listTableBodyContent.jsp"/> 
	</tbody>
</table>
</fmt:bundle>