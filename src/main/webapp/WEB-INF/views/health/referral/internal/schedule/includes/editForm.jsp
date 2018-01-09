<%--
 - Author: Ryan Johns
 - Author: Stephen Abson
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Apr 16, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<form:form commandName="scheduleForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="referralLegendLabel"/></legend>
			<c:if test="${operation.name eq 'RESCHEDULE'}">
				<form:hidden path="statusReasonRequired"/>
				<span class="fieldGroup">
					<form:label path="statusReason" class="fieldLabel">
						<fmt:message key="internalReferralRescheduleStatusReasonLabel"/></form:label>
					<form:select path="statusReason">
						<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
						<form:options itemLabel="name" itemValue="id" items="${statusReasons}"/>
					</form:select>
					<form:errors path="statusReason" cssClass="error"/>
				</span>
			</c:if>
			<form:hidden path="offenderRequired"/>
			<c:if test="${scheduleForm.offenderRequired}">
				<span class="fieldGroup">
					<form:label path="offender" class="fieldLabel"><fmt:message key="offenderLabel"/></form:label>
						<c:choose>
							<c:when test="${not empty scheduleForm.offender}">
								<input id="offenderName" type="text" value="<c:out value='${scheduleForm.offender.name.firstName}'/> <c:out value='${scheduleForm.offender.name.lastName}'/>"/>
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
				<label class="fieldLabel"><fmt:message key="offenderUnitLabel"/></label>
				<input type="text" readonly="readonly" id="offenderUnitLabel" value="<c:out value='${unitAbbreviation}'/>"/>
			</span>
			<span class="fieldGroup">
				<form:label path="reason" class="fieldLabel">
					<fmt:message key="internalReferralReasonLabel"/>
				</form:label>
				<form:select path="reason">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemLabel="name" itemValue="id" items="${reasons}"/>
				</form:select>
				<form:errors cssClass="error" path="reason"/>
			</span>
			<span class="fieldGroup">
				<form:label path="locationDesignator" class="fieldLabel">
					<fmt:message key="locationDesignatorLabel"/>
				</form:label>
				<form:select path="locationDesignator">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<c:forEach items="${referralDesignationLocations}" var="referralDesignator">
						<form:option value="${referralDesignator.name}"><fmt:message key="referralLocationDesignatorLabel.${referralDesignator.name}"/></form:option> 
					</c:forEach>				
				</form:select>
				<form:errors cssClass="error" path="locationDesignator"/>
			</span>
			<span class="fieldGroup">
				<form:label path="providerLevel" class="fieldLabel">
					<fmt:message key="providerLevelLabel"/>
				</form:label>
				<form:select path="providerLevel">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemLabel="name" itemValue="id" items="${providerLevels}"/>
				</form:select>
				<form:errors cssClass="error" path="providerLevel"/>
			</span>
			<span class="fieldGroup">
				<form:label path="providerAssignment" class="fieldLabel">
					<fmt:message key="providerAssignmentLabel"/>
				</form:label>
				<form:select path="providerAssignment">
					<jsp:include page="/WEB-INF/views/health/referral/internal/includes/providerAssignmentOptions.jsp"/>
				</form:select>
				<form:errors cssClass="error" path="providerAssignment"/>
			</span>
			<span class="fieldGroup" id="internalWeeklyProviderScheduleContainer">
				<c:set var="form" value="${scheduleForm}" scope="request"/>
				<c:set var="scheduleDate" value="${scheduleForm.scheduleDate}" scope="request"/>
				<form:errors cssClass="error" path="scheduleDate"/>
				<jsp:include page="/WEB-INF/views/health/provider/schedule/internal/includes/weeklyTable.jsp"/>
			</span>
			<span class="fieldGroup">
				<form:label path="notes" class="fieldLabel">
					<fmt:message key="internalReferralSchedulingNotesLabel"/>
				</form:label>
				<form:textarea path="notes" />
				<form:errors cssClass="error" path="notes"/>
			</span>
		</fieldset>
		<sec:authorize access="hasRole('DISABLED')">
		<fieldset>
			<legend><fmt:message key="requiredLabsLegendLabel"/></legend>
			<c:set var="labWorkItems" value="${scheduleForm.labWork}" scope="request"/>
				<a href="${pageContext.request.contextPath}/health/referral/labWork/addLabWorkItem.html" class="createLink" id="addLabWorkLink">
					<span class="visibleLinkLabel"><fmt:message key="createLabWorkLink"/></span></a>
			<jsp:include page="/WEB-INF/views/health/labWork/includes/defaultSelections.jsp"/>	
			<jsp:include page="/WEB-INF/views/health/labWork/includes/labWorkContent.jsp"/>
		</fieldset>
		</sec:authorize>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
</form:form>
</fmt:bundle>