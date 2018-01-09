<%--
 - Author: Joel Norris
 - Version: 0.1.0 (July 16, 2014)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<form:form commandName="scheduleLabWorkReferralForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="referralLegendLabel"/></legend>
			<c:if test="${operation.name eq 'RESCHEDULE'}">
				<span class="fieldGroup">
					<form:label path="statusReason" class="fieldLabel"><fmt:message key="labWorkReferralStatusReasonLabel"/></form:label>
					<form:select path="statusReason">
						<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
						<form:options itemLabel="name" itemValue="id" items="${statusReasons}"/>
					</form:select>
					<form:errors path="statusReason" cssClass="error"/>
				</span>
			</c:if>
			<form:hidden path="offenderRequired"/>
			<c:if test="${scheduleLabWorkReferralForm.offenderRequired}">
				<span class="fieldGroup">
					<form:label path="offender" class="fieldLabel"><fmt:message key="offenderLabel"/></form:label>
						<c:choose>
							<c:when test="${not empty scheduleLabWorkReferralForm.offender}">
								<input id="offenderName" type="text" value="<c:out value='${scheduleLabWorkReferralForm.offender.name.lastName}'/>, <c:out value='${scheduleLabWorkReferralForm.offender.name.firstName}'/> <c:out value='${scheduleLabWorkReferralForm.offender.name.middleName}'/> (<c:out value='${scheduleLabWorkReferralForm.offender.offenderNumber}'/>)"/>
							</c:when>
							<c:otherwise>
								<input id="offenderName" type="text"/>
							</c:otherwise>
						</c:choose>
					<form:input id="offender" path="offender" type="hidden"/>
					<form:errors cssClass="error" path="offender"/>
				</span>
			</c:if>
			<span class="fieldGroup">
				<form:label path="sampleDate" class="fieldLabel"><fmt:message key="sampleDateLabel"/></form:label>
				<form:input path="sampleDate" class="date"/>
				<form:errors path="sampleDate" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="schedulingNotes" class="fieldLabel"><fmt:message key="labWorkSchedulingNotesLabel"/></form:label>
				<form:textarea path="schedulingNotes"/>
				<form:errors path="schedulingNotes" cssClass="error"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="requiredLabsLegendLabel"/></legend>
			<c:set var="labWorkSampleItems" value="${scheduleLabWorkReferralForm.labWorkSampleItems}" scope="request"/>
				<a href="${pageContext.request.contextPath}/health/labWork/addLabWorkSampleItem.html" class="createLink" id="addLabWorkSampleItemLink">
					<span class="visibleLinkLabel"><fmt:message key="createLabWorkLink"/></span></a>
			<jsp:include page="/WEB-INF/views/health/referral/labWork/includes/defaultSelections.jsp"/>
			<jsp:include page="/WEB-INF/views/health/referral/labWork/schedule/includes/labWorkSampleContent.jsp"/>
			<form:errors path="labWorkSampleItems" cssClass="error"/>
		</fieldset>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</form:form>
</fmt:bundle>