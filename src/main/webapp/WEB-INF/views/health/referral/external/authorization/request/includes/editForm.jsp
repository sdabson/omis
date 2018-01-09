<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form commandName="requestExternalReferralAuthorizationForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="requestExternalReferralAuthorizationLabel" bundle="${healthBundle}"/></legend>
		<form:hidden path="offenderRequired"/>
		<c:if test="${requestExternalReferralAuthorizationForm.offenderRequired}">
				<span class="fieldGroup">
					<form:label path="offender" class="fieldLabel"><fmt:message key="offenderLabel" bundle="${healthBundle}"/></form:label>
						<c:choose>
							<c:when test="${not empty requestExternalReferralAuthorizationForm.offender}">
								<input id="offenderName" type="text" value="<c:out value='${requestExternalReferralAuthorizationForm.offender.name.lastName}, ${requestExternalReferralAuthorizationForm.offender.name.firstName} ${requestExternalReferralAuthorizationForm.offender.name.middleName} (${requestExternalReferralAuthorizationForm.offender.offenderNumber})'/>"/>
							</c:when>
							<c:otherwise>
								<input id="offenderName" type="text"/>
							</c:otherwise>
						</c:choose>
					<form:input id="offender" path="offender" type="hidden"/>
					<form:errors cssClass="error" path="offender"/>
				</span>
		</c:if>
		<c:choose>
				<c:when test="${empty unitAbbreviation}">
					<c:set var="unitGroupClass" value="fieldGroup hidden"/>
				</c:when>
				<c:otherwise>
					<c:set var="unitGroupClass" value="fieldGroup"/>
				</c:otherwise>
		</c:choose>
		<span id="offenderUnitGroup" class="<c:out value='${unitGroupClass}'/>">
			<label class="fieldLabel"><fmt:message key="offenderUnitLabel" bundle="${healthBundle}"/></label>
			<input type="text" readonly="readonly" id="offenderUnitLabel" value="<c:out value='${unitAbbreviation}'/>"/>
		</span>
		<span class="fieldGroup">
			<form:label path="medicalFacility" class="fieldLabel"><fmt:message key="medicalFacilityLabel" bundle="${healthBundle}"/></form:label>
			<form:select path="medicalFacility">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${medicalFacilities}"/>
			</form:select>
			<form:errors path="medicalFacility" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="providerAssignment" class="fieldLabel"><fmt:message key="externalProviderAssignmentLabel" bundle="${healthBundle}"/></form:label>
				<form:select path="providerAssignment">
						<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
						<c:forEach var="providerAssignment" items="${providerAssignments}">
							<c:choose>
								<c:when test="${providerAssignment eq requestExternalReferralAuthorizationForm.providerAssignment}">
									<option value="${providerAssignment.id}" selected="selected">
										<c:out value="${providerAssignment.provider.name.lastName}"/>,
										<c:out value="${providerAssignment.provider.name.firstName}"/>
										<c:out value="${providerAssignment.title.abbreviation}"/>
									</option>
								</c:when>
								<c:otherwise>
									<option value="${providerAssignment.id}">
										<c:out value="${providerAssignment.provider.name.lastName}"/>,
										<c:out value="${providerAssignment.provider.name.firstName}"/>
										<c:out value="${providerAssignment.title.abbreviation}"/>
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</form:select>
			<form:errors path="providerAssignment" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="reason" class="fieldLabel"><fmt:message key="externalReferralReasonLabel" bundle="${healthBundle}"/></form:label>
			<form:select path="reason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${reasons}"/>
			</form:select>
			<form:errors path="reason" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="reasonNotes" class="fieldLabel"><fmt:message key="externalReferralReasonNotesLabel" bundle="${healthBundle}"/></form:label>
			<form:textarea path="reasonNotes"/>
			<form:errors path="reasonNotes" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="referredByProviderAssignment" class="fieldLabel"><fmt:message key="referredByProviderAssignmentLabel" bundle="${healthBundle}"/></form:label>
				<form:select path="referredByProviderAssignment">
						<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
						<c:forEach var="referredByProviderAssignment" items="${referredByProviderAssignments}">
							<c:choose>
								<c:when test="${referredByProviderAssignment eq requestExternalReferralAuthorizationForm.referredByProviderAssignment}">
									<option value="${referredByProviderAssignment.id}" selected="selected">
										<c:out value="${referredByProviderAssignment.provider.name.lastName}"/>,
										<c:out value="${referredByProviderAssignment.provider.name.firstName}"/>
										<c:out value="${referredByProviderAssignment.title.abbreviation}"/>
										<c:if test="${not empty referredByProviderAssignment.medicalFacility}">
										- <c:out value="${referredByProviderAssignment.medicalFacility.name}"/>
										</c:if>
									</option>
								</c:when>
								<c:otherwise>
									<option value="${referredByProviderAssignment.id}">
										<c:out value="${referredByProviderAssignment.provider.name.lastName}"/>,
										<c:out value="${referredByProviderAssignment.provider.name.firstName}"/>
										<c:out value="${referredByProviderAssignment.title.abbreviation}"/>
										<c:if test="${not empty referredByProviderAssignment.medicalFacility}">
										- <c:out value="${referredByProviderAssignment.medicalFacility.name}"/>
										</c:if>
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</form:select>
			<form:errors path="referredByProviderAssignment" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="referredDate" class="fieldLabel"><fmt:message key="externalReferralReferredDateLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="referredDate" id="referredDate" class="date"/>
			<form:errors path="referredDate" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="externalReferralAuthorizationRequestLegend" bundle="${healthBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="authorize" class="fieldLabel"><fmt:message key="authorizeExternalReferralRequestLabel" bundle="${healthBundle}"/></form:label>
			<form:checkbox path="authorize" id="authorize" />
			<form:errors path="authorize" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="decisionDate" class="fieldLabel"><fmt:message key="requestExternalReferralAuthorizationDecisionDateLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="decisionDate" id="decisionDate" class="date"/>
			<form:errors path="decisionDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="authorizedByText" class="fieldLabel"><fmt:message key="requestExternalReferralAuthorizationAuthorizedByLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="authorizedByText" id="authorizedByText"/>
			<form:hidden path="authorizedBy" id="authorizedBy"/>
			<form:errors path="authorizedByText" cssClass="error"/>
			<a id="clearCurrentUserLink" class="fieldLink clearLink" href="${pageContext.request.contextPath}/health/referral/external/authorization/clearUser.html?authorization=${authorization.id}" title="<fmt:message key='clearLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="clearLink" bundle="${commonBundle}"/></span></a>
			<a id="useCurrentUserLink" class="fieldLink currentUserLink" href="${pageContext.request.contextPath}/health/referral/external/authorization/useCurrentUser.html?authorization=${authorization.id}" title="<fmt:message key='useCurrentUserLink' bundle='${auditBundle}'/>"><span class="linkLabel"><fmt:message key="useCurrentUserLink" bundle="${auditBundle}"/></span></a>
			<form:label id="authorizedByLabel" path="authorizedByLabel" class="fieldValueLabel">
				<c:out value="${requestExternalReferralAuthorizationForm.authorizedByLabel}"/></form:label>
		</span>
		<span class="fieldGroup">
			<form:label path="authorizationNotes" class="fieldLabel"><fmt:message key="requestExternalReferralAuthorizationNotesLabel" bundle="${healthBundle}"/></form:label>
			<form:textarea path="authorizationNotes"/>
			<form:errors path="authorizationNotes" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="status" class="fieldLabel"><fmt:message key="requestExternalReferralAuthorizationStatusLabel" bundle="${healthBundle}"/></form:label>
			<form:select path="status" id="status">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="status" items="${statuses}">
					<form:option value="${status.name}"><fmt:message key="requestExternalReferralAuthorizationStatusLabel.${status.name}" bundle="${healthBundle}"/></form:option>
				</c:forEach>
			</form:select>
			<form:errors path="status" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="reviewDate" class="fieldLabel"><fmt:message key="requestExternalReferralAuthorizationReviewDateLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="reviewDate" id="reviewDate" class="date"/>
			<form:errors path="reviewDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="medicalPanelReviewDecisionStatus" class="fieldLabel"><fmt:message key="requestExternalReferralAuthorizationMedicalPanelReviewDecisionStatusLabel" bundle="${healthBundle}"/></form:label>
			<form:select path="medicalPanelReviewDecisionStatus" id="medicalPanelReviewDecisionStatus">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="medicalPanelReviewDecisionStatus" items="${medicalPanelReviewDecisionStatuses}">
					<form:option value="${medicalPanelReviewDecisionStatus.name}"><fmt:message key="medicalPanelReviewDecisionStatusLabel.${medicalPanelReviewDecisionStatus.name}" bundle="${healthBundle}"/></form:option>
				</c:forEach>
			</form:select>
			<form:errors path="medicalPanelReviewDecisionStatus" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>