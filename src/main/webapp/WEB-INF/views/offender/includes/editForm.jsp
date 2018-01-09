<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="nameBundle" basename="omis.person.msgs.name"/>
<fmt:setBundle var="identityBundle" basename="omis.person.msgs.identity"/>
<fmt:setBundle var="religionBundle" basename="omis.religion.msgs.religion"/>
<fmt:setBundle var="mediaBundle" basename="omis.media.msgs.media"/>
<fmt:setBundle var="offenderBundle" basename="omis.offender.msgs.offender"/>
<fmt:setBundle var="offenderFlagBundle" basename="omis.offenderflag.msgs.offenderFlag"/>
<form:form commandName="createOffenderForm" method="post" enctype="multipart/form-data" class="editForm">
	<fieldset>
		<legend><fmt:message key="nameLabel" bundle="${nameBundle}"/></legend>
		<jsp:include page="/WEB-INF/views/person/name/includes/nameFields.jsp"/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="identityLabel" bundle="${identityBundle}"/></legend>
		<c:set var="createNewBirthPlace" value="${createOffenderForm.createNewBirthPlace}" scope="request"/>
		<jsp:include page="/WEB-INF/views/person/identity/includes/identityFields.jsp"/>
	</fieldset>
	<jsp:include page="/WEB-INF/views/demographics/includes/fieldsets.jsp"/>
	<fieldset>
		<legend><fmt:message key="religiousPreferenceLabel" bundle="${religionBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="religion" class="fieldLabel">
				<fmt:message key="religionLabel" bundle="${religionBundle}"/></form:label>
			<form:select path="religion">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var="religion" items="${religions}">
					<c:choose>
						<c:when test="${offenderForm.religion eq religion}">
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
	</fieldset>
	<jsp:include page="/WEB-INF/views/offender/citizenshipAndImmigration/includes/fieldset.jsp"/>
	<fieldset>
		<legend><fmt:message key="offenderFlagsLabel" bundle="${offenderFlagBundle}"/></legend>
		<c:forEach var="flagItem" items="${createOffenderForm.flagItems}" varStatus="status">
			<span class="fieldGroup">
				<label class="fieldLabel"><c:out value="${flagItem.category.name}"/></label>
				<input type="hidden" name="flagItems[${status.index}].category" value="${flagItem.category.id}"/>
				<c:choose>
					<c:when test="${createOffenderForm.flagItems[status.index].value.name eq 'YES'}">
						<input class="fieldValue" type="radio" id="flagItems[${status.index}].yesValue" name="flagItems[${status.index}].value" value="YES" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input class="fieldValue" type="radio" id="flagItems[${status.index}].yesValue" name="flagItems[${status.index}].value" value="YES"/>
					</c:otherwise>
				</c:choose>
				<label class="fieldLabelValue" for="flagItems[${status.index}].yesValue"><fmt:message key="yesLabel" bundle="${commonBundle}"/></label>
				<c:choose>
					<c:when test="${createOffenderForm.flagItems[status.index].value.name eq 'NO'}">
						<input class="fieldValue" type="radio" id="flagItems[${status.index}].noValue" name="flagItems[${status.index}].value" value="NO" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input class="fieldValue" type="radio" id="flagItems[${status.index}].noValue" name="flagItems[${status.index}].value" value="NO"/>
					</c:otherwise>
				</c:choose>
				<label class="fieldLabelValue" for="flagItems[${status.index}].noValue"><fmt:message key="noLabel" bundle="${commonBundle}"/></label>
				<c:choose>
					<c:when test="${createOffenderForm.flagItems[status.index].value.name eq 'UNKNOWN'}">
						<input class="fieldValue" type="radio" id="flagItems[${status.index}].unknownValue" name="flagItems[${status.index}].value" value="UNKNOWN" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input class="fieldValue" type="radio" id="flagItems[${status.index}].unknownValue" name="flagItems[${status.index}].value" value="UNKNOWN"/>
					</c:otherwise>
				</c:choose>
				<label class="fieldLabelValue" for="flagItems[${status.index}].unknownValue"><fmt:message key="unknownLabel" bundle="${commonBundle}"/></label>
				<form:errors path="flagItems[${status.index}]" cssClass="error"/>
			</span>
		</c:forEach>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="photoLabel" bundle="${mediaBundle}"/></legend>
		<span class="fieldGroup" id="photoPreviewFieldGroup">
			<label class="fieldLabel"><fmt:message key="photoPreviewLabel" bundle="${mediaBundle}"/></label>
			<img id="photoPreview" height="120" width="180" title="<fmt:message key='photoPreviewLabel' bundle="${mediaBundle}"/>"/>
		</span>
		<span class="fieldGroup">
		<label for="photoData" class="fieldLabel">
			<fmt:message key="photoFileLabel" bundle="${mediaBundle}"/></label>
		<input id="photoData" type="file" name="photoData" accept="image/jpeg"/>
		<form:errors path="photoData" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="photoDate" class="fieldLabel">
		  <fmt:message key="photoDateLabel" bundle="${mediaBundle}"/></form:label>
		<form:input path="photoDate" class="date"/>
		<form:errors path="photoDate" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='createOffenderHeader' bundle='${offenderBundle}'/>"/>
	</p>
</form:form>