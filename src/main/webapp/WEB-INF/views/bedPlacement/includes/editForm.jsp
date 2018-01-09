<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
<form:form commandName="bedPlacementForm" class="editForm">
	<input type="hidden" id="offenderId" value="${offender.id}"/>
	<c:if test="${not empty facility}">
		<h3>
			<fmt:message key="currentFacilityLabel">
				<fmt:param value="${facility.name}"/>
			</fmt:message>
		</h3>
	</c:if>
	<c:if test="${not empty  bedPlacementForm.activeBedPlacement}">
		<fieldset>
			<legend><fmt:message key="activeBedPlacementLegendLabel"/></legend>
			<c:set var="bedPlacementSummary" value="${activeBedPlacementSummary}" scope="request"/>
			<jsp:include page="bedPlacementSummary.jsp"/>
		</fieldset>
	</c:if>
	<fieldset>
		<legend><fmt:message key="areaFilterLabel"/></legend>
		<form:input type="hidden" path="facility" value="${facility.id}"/>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="complex"><fmt:message key="complexSelectLabel"/></form:label>
			<form:select path="complex">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${matchingComplexes}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="complex"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="unit"><fmt:message key="unitSelectLabel"/></form:label>
			<form:select path="unit">
				<jsp:include page="unitOptions.jsp"/>
			</form:select>
			<form:errors cssClass="error" path="unit"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="section"><fmt:message key="sectionSelectLabel"/></form:label>
			<form:select path="section">
				<jsp:include page="sectionOptions.jsp"/>
			</form:select>
			<form:errors cssClass="error" path="section"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="level"><fmt:message key="levelSelectLabel"/></form:label>
			<form:select path="level">
				<jsp:include page="levelOptions.jsp"/>
			</form:select>
			<form:errors cssClass="error" path="level"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="placementDescriptionLabel"/></legend>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="room"><fmt:message key="roomSelectLabel"/></form:label>
			<form:select path="room">
				<jsp:include page="roomOptions.jsp"/>
			</form:select>
			<form:errors cssClass="error" path="room"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="complex"><fmt:message key="bedSelectLabel"/></form:label>
			<form:select path="bed">
				<jsp:include page="bedOptions.jsp"/>
			</form:select>
			<form:errors cssClass="error" path="bed"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="bedPlacementReason"><fmt:message key="bedPlacementReasonLabel"/></form:label>
			<form:select path="bedPlacementReason">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${bedPlacementReasons}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="bedPlacementReason"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel"><fmt:message key="bedPlacementStartDateLabel"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors cssClass="error" path="startDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startTime" class="fieldLabel"><fmt:message key="bedPlacementStartTimeLabel"/></form:label>
			<form:input path="startTime" class="date"/>
			<form:errors cssClass="error" path="startTime"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel"><fmt:message key="bedPlacementEndDateLabel"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors cssClass="error" path="endDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endTime" class="fieldLabel"><fmt:message key="bedPlacementEndTimeLabel"/></form:label>
			<form:input path="endTime" class="date"/>
			<form:errors cssClass="error" path="endTime"/>
		</span>
		<span class="fieldGroup">
			<form:label path="confirmed" class="fieldLabel"><fmt:message key="bedPlacementConfirmationLabel"/></form:label>
			<form:checkbox path="confirmed"/>
		</span>
		<span class="fieldGroup">
			<form:label path="description" class="fieldLabel"><fmt:message key="bedPlacementDescriptionLabel"/></form:label>
			<form:textarea path="description"/>
			<form:errors cssClass="error" path="description"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endActiveBedPlacement" class="fieldLabel"><fmt:message key="endActiveBedPlacementLabel"/></form:label>
			<form:checkbox path="endActiveBedPlacement" id="endActiveBedPlacement" value="true"/>
			<form:input type="hidden" path="activeBedPlacement"/>
		</span>
	</fieldset>
	
	<c:if test="${not empty bedPlacement}">
		<c:set var="updatable" value="${bedPlacement}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='bedPlacementSaveLabel'/>"/>
	</p>
</form:form>	
</fmt:bundle>