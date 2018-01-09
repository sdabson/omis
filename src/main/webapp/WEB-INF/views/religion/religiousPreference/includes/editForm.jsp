<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.religion.msgs.religion" var="religionBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<form:form commandName="religiousPreferenceForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="religiousPreferenceLabel" bundle="${religionBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="religion" class="fieldLabel">
				<fmt:message key="religionLabel" bundle="${religionBundle}"/></form:label>
			<form:select path="religion">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="religion" items="${religions}">
					<c:choose>
						<c:when test="${religiousPreferenceForm.religion eq religion}">
							<form:option value="${religion}" selected="true"><c:out value="${religion.name}"/> (<c:out value="${religion.group.name}"/>)</form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${religion}"><c:out value="${religion.name}"/> (<c:out value="${religion.group.name}"/>)</form:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="religion" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="comment" class="fieldLabel">
				<fmt:message key="commentLabel" bundle="${religionBundle}"/></form:label>
			<form:textarea path="comment"/>
			<form:errors path="comment" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty religiousPreferenceForm.accommodationAuthorizationItems}">
	<fieldset>
		<legend><fmt:message key="authorizedAccommodationsLabel" bundle="${religionBundle}"/></legend>
		<c:forEach var="accommodationAuthorizationItem" items="${religiousPreferenceForm.accommodationAuthorizationItems}" varStatus="status">
		<span class="fieldGroup">
			<label for="accommodationAuthorizationItems[${status.index}].authorized" class="fieldLabel">
				<c:out value="${accommodationAuthorizationItem.accommodation.name}"/></label>
			<c:choose>
				<c:when test="${accommodationAuthorizationItem.authorized}">
					<input type="checkbox" name="accommodationAuthorizationItems[${status.index}].authorized" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="accommodationAuthorizationItems[${status.index}].authorized"/>
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="accommodationAuthorizationItems[${status.index}].accommodation" value="${accommodationAuthorizationItem.accommodation.id}"/>
		</span>
		</c:forEach>
		<form:errors path="accommodationAuthorizationItems" cssClass="errors"/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="religiousPreferenceVerificationSignatureLegend" bundle="${religionBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="verificationResult" class="fieldLabel">
				<fmt:message key="religiousPreferenceVerificationResultLabel" bundle="${religionBundle}"/></form:label>
			<form:checkbox path="verificationResult"/>
			<form:errors path="verificationResult" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationUserAccount" class="fieldLabel">
				<fmt:message key="religiousPreferenceVerificationUserAccountLabel" bundle="${religionBundle}"/></form:label>
			<input type="text" id="verificationUserAccountQuery"/>
			<a id="clearVerificationUserAccountLink" class="fieldLink clearLink" href="${pageContext.request.contextPath}/religion/religiousPreferences/clearVerificationUserAccount.html?preference=${preference.id}" title="<fmt:message key='clearLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="clearLink" bundle="${commonBundle}"/></span></a>
			<a id="useCurrentUserAccountForVerificationLink" class="fieldLink currentUserAccountLink" href="${pageContext.request.contextPath}/religion/religiousPreferences/useCurrentUserAccountForVerification.html?preference=${preference.id}" title="<fmt:message key='useCurrentUserAccountLink' bundle='${auditBundle}'/>"><span class="linkLabel"><fmt:message key="useCurrentUserAccountLink" bundle="${auditBundle}"/></span></a>
			<form:label id="verificationUserAccountLabel" path="verificationUserAccount" class="fieldValueLabel">
				<c:out value="${religiousPreferenceForm.verificationUserAccountLabel}"/></form:label>
			<form:hidden path="verificationUserAccount"/>
			<form:errors path="verificationUserAccount" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationDate" class="fieldLabel">
				<fmt:message key="verificationDateLabel" bundle="${auditBundle}"/></form:label>
			<form:input path="verificationDate" class="date"/>
			<form:errors path="verificationDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationMethod" class="fieldLabel">
				<fmt:message key="verificationMethodLabel" bundle="${auditBundle}"/></form:label>
			<form:select path="verificationMethod">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${verificationMethods}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="verificationMethod" cssClass="error"/>
		</span>
	</fieldset>
	</c:if>
	<c:if test="${not empty preference}">
		<c:set var="updatable" value="${preference}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>