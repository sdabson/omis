<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<form:form commandName="visitForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="visitDetailsLabel"/></legend>
				<span class="fieldGroup">
					<c:set value="${visitForm.visitationAssociation}" var="visitor" scope="request"/>
					<form:label path="visitationAssociation" class="fieldLabel"><fmt:message key="visitVisitationAssociationLabel"/></form:label>
					<form:select path="visitationAssociation">
						<jsp:include page="visitationAssociationOptions.jsp"/>
					</form:select>
					<form:errors cssClass="error" path="visitationAssociation"/>
				</span>
<!-- 				Remove the hidden span when multiple visitation methods are allowed -->
				<span class="removeThisSpan hidden">
					<span class="fieldGroup hidden">
						<form:label path="visitMethod" class="fieldLabel"><fmt:message key="visitMethodLabel"/></form:label>
						<form:select path="visitMethod">
							<jsp:include page="../../../includes/nullOption.jsp"/>
							<c:forEach items="${visitMethods}" var="visitMethod" varStatus="index">
								<c:choose>
									<c:when test="${visitForm.visitMethod eq visitMethod}">
										<form:option value="${visitMethod}" selected="true"><fmt:message key="visitMethod.${visitMethod}.label"/></form:option>
									</c:when>
									<c:otherwise>
										<form:option value="${visitMethod}"><fmt:message key="visitMethod.${visitMethod}.label"/></form:option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<form:errors cssClass="error" path="visitMethod"/>
					</span>
				</span>
				<span class="fieldGroup">
					<form:label path="badgeNumber" class="fieldLabel"><fmt:message key="visitBadgeNumberLabel"/></form:label>
					<form:input path="badgeNumber" type="text"/>
					<form:errors cssClass="error" path="badgeNumber"/>
				</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="timeDetailsLabel"/></legend>
				<span class="fieldGroup">
					<form:label path="date" class="fieldLabel"><fmt:message key="visitDateLabel"/></form:label>
					<form:input path="date" class="date"/>
					<form:errors cssClass="error" path="date"/>
				</span>
				<span class="fieldGroup">
					<form:label path="startTime" class="fieldLabel"><fmt:message key="visitStartTimeLabel"/></form:label>
					<form:input path="startTime" class="time"/>
					<form:errors cssClass="error" path="startTime"/>
				</span>
				<span class="fieldGroup">
					<form:label path="endTime" class="fieldLabel"><fmt:message key="visitEndTimeLabel"/></form:label>
					<form:input path="endTime" class="time"/>
					<form:errors cssClass="error" path="endTime"/>
				</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="refusalDetailsLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="deniedByStaff" class="fieldLabel"><fmt:message key="deniedByStaffLabel"/></form:label>
				<form:checkbox path="deniedByStaff"/>
				<form:errors cssClass="error" path="deniedByStaff"/>
			</span>
			<span class="fieldGroup">
				<form:label path="refusedByOffender" class="fieldLabel"><fmt:message key="refusedByOffenderLabel"/></form:label>
				<form:checkbox path="refusedByOffender"/>
				<form:errors cssClass="error" path="refusedByOffender"/>
			</span>
		</fieldset>
		<c:if test="${not empty visit}">
			<c:set var="updatable" value="${visit}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveVisitLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>