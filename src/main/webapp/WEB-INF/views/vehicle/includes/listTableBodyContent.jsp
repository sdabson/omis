<%--
 - Author: Ryan Johns
 - Author: Yidong Li
 - Version: 0.1.0 (Apr 5, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<c:forEach var="offenderVehicleSummary" items="${offenderVehicleSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem rowMenuItem" id="actionMenuLink${status.index}" href="${pageContext.request.contextPath}/vehicle/vehicleRowActionMenu.html?vehicleAssociation=${offenderVehicleSummary.vehicleAssociationId}&offender=${offenderSummary.id}"></a>		
		</td>
		<td><fmt:formatDate value="${offenderVehicleSummary.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${offenderVehicleSummary.endDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${offenderVehicleSummary.year}"/></td>
		<td><c:out value="${offenderVehicleSummary.vehicleMakeName}"/></td>
		<td><c:out value="${offenderVehicleSummary.vehicleModelName}"/></td>
		<td><c:out value="${offenderVehicleSummary.vehicleColorName}"/></td>
		<td><c:if test="${not empty offenderVehicleSummary.stateName}"> <c:out value="[${offenderVehicleSummary.stateName}]"/></c:if><c:out value="${offenderVehicleSummary.plateNumber}"/></td>
		<td><c:out value="${offenderVehicleSummary.ownerDescription}"/></td>
	</tr>
</c:forEach>