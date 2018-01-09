<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<span class="fieldGroup">
		<label for="externalOrganization" class="fieldLabel"><fmt:message key="organizationLabel"/></label>
		<c:choose>
			<c:when test="${not empty providerAssignment}">
				<input type="text" readonly="readonly" value="${providerAssignment.externalOrganization.name}"/>
				<input type="hidden" name="externalOrganization" value="${providerAssignment.externalOrganization.id}"/>
			</c:when>
			<c:otherwise>
					<select name="externalOrganization" >
						<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
						<c:forEach var="externalOrganization" items="${externalOrganizations}">
							<c:choose>
								<c:when test="${providerAssignmentForm.externalOrganization eq externalOrganization}">
									<option value="${externalOrganization.id}" selected="selected"><c:out value="${externalOrganization.name}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${externalOrganization.id}"><c:out value="${externalOrganization.name}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
			</c:otherwise>
		</c:choose>
		<form:errors cssClass="error" path="externalOrganization"/>
	</span>
</fmt:bundle>