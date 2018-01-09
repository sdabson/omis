<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Aug 25, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
	<table id="workAssignment" class="listTable">
		<thead>
			<tr>
				<th class = "operations"> </th>
				<th><fmt:message key="assignmentLabel"/></th>
				<th><fmt:message key="assignedDateLabel"/></th>
				<th><fmt:message key="terminationDateLabel"/></th>
				<th><fmt:message key="daysLabel"/></th>
				<th><fmt:message key="fenceRestrictionLabel"/></th>
				<th><fmt:message key="commentsLabel"/></th>
			</tr>
		</thead>
		<tbody>
			<jsp:include page="listTableBodyContent.jsp"/>
		</tbody>
	</table>
</fmt:bundle>