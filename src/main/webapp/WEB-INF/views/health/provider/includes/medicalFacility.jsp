<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<span class="fieldGroup">
		<label for="medicalFacility" class="fieldLabel"><fmt:message key="medicalFacilityLabel"/></label>
		<c:choose>
			<c:when test="${not empty providerAssignment}">
				<input type="text" readonly="readonly" value="${providerAssignment.medicalFacility.name}"/>
				<input type="hidden" name="medicalFacility" value="${providerAssignment.medicalFacility.id}"/>
			</c:when>
			<c:otherwise>
					<select name="medicalFacility" >
						<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
						<c:forEach var="medicalFacility" items="${medicalFacilities}">
							<c:choose>
								<c:when test="${providerAssignmentForm.medicalFacility eq medicalFacility}">
									<option value="${medicalFacility.id}" selected="selected"><c:out value="${medicalFacility.name}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${medicalFacility.id}"><c:out value="${medicalFacility.name}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
			</c:otherwise>
		</c:choose>
		<form:errors cssClass="error" path="medicalFacility"/>
	</span>
</fmt:bundle>