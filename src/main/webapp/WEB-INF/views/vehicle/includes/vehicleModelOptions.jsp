<%--
 - Author: Yidong Li
 - Date : Oct.20
 - Display selected vehicle model.
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
	<c:forEach var="vehicleModel" items="${vehicleModels}">
		<c:choose>
		<c:when test="${selectedVehicleModel eq vehicleModel}">
			<option value="${selectedVehicleModel.id}" selected="selected"><c:out value="${selectedVehicleModel.name}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${vehicleModel.id}"><c:out value="${vehicleModel.name}"/></option>
		</c:otherwise>
		</c:choose>
	</c:forEach>