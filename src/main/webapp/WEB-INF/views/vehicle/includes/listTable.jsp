<%--
 - Author: Ryan Johns
 - Author: Yidong Li
 - Version: 0.1.0 (JUl 23, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.vehicle.msgs.vehicle">
<table id="vehicles" class="listTable">
	<thead>
		<tr>
		<th class="operations"/>
		<th><fmt:message key="startDateLabel"/></th>
		<th><fmt:message key="endDateLabel"/></th>
		<th><fmt:message key="yearLabel"/></th>
		<th><fmt:message key="vehicleMakeLabel"/></th>
		<th><fmt:message key="vehicleModelLabel"/></th>
		<th><fmt:message key="vehicleColorLabel"/></th>
		<th><fmt:message key="plateNumberLabel"/></th>
		<th><fmt:message key="ownerLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>