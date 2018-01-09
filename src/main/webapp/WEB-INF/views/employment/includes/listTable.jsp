<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.employment.msgs.employment">
<table id="employments" class="listTable">
	<thead>
		<tr>
		<th> </th>
		<th><fmt:message key="startDateLabel"/></th>
		<th><fmt:message key="endDateLabel"/></th>
		<th><fmt:message key="employerLabel"/></th>
		<th><fmt:message key="jobTitleLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>