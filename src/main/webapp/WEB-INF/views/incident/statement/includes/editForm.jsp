<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.incident.msgs.incident">
	<form:form commandName="incidentStatementForm" class="editForm">
		<c:set value="${incidentStatementForm}" var="incidentStatementForm" scope="request"/>
		<c:set value="${incidentStatementForm.informationSourceCategory}" var="informationSourceCategory" scope="request"/>
		<fieldset>
			<legend><fmt:message key="incidentStatementDetailsLabel"/></legend>
			<c:if test="${not empty incidentStatementNumber}">
				<span class="fieldGroup">
					<label class="fieldLabel"><fmt:message key="reportNumberLabel"/></label>
					<label><c:out value="${incidentStatementNumber}"/></label>
				</span>
			</c:if>
			<span class="fieldGroup">
				<form:label path="title" class="fieldLabel"><fmt:message key="titleLabel"/></form:label>
				<form:input path="title" size="50" />
				<form:errors cssClass="error" path="title"/>
			</span>
			<span class="fieldGroup">
					<form:label class="fieldLabel" path="category"><fmt:message key="categoryLabel"/></form:label>
					<form:select path="category" >
						<jsp:include page="../../../includes/nullOption.jsp"/>
						<form:options items="${categories}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:errors cssClass="error" path="category"/>
				</span>
			<span class="fieldGroup">
				<form:label path="incidentDate" class="fieldLabel"><fmt:message key="incidentDateLabel"/></form:label>
				<form:input path="incidentDate" class="date"/>
				<form:errors cssClass="error" path="incidentDate"/>
			</span>
			<span class="fieldGroup">
				<form:label path="incidentTime" class="fieldLabel"><fmt:message key="incidentTimeLabel"/></form:label>
				<form:input path="incidentTime" class="time"/>
				<form:errors cssClass="error" path="incidentTime"/>
			</span>
			<span class="fieldGroup">
				<fmt:message key="detailsDescriptionLabel" var="detailsDescription"/>
				<form:label path="summary" class="fieldLabel"><fmt:message key="detailsLabel"/></form:label>
				<form:textarea path="summary" maxlength="32760" rows="15" placeholder="${detailsDescription}"/>
				<span class="characterCounter" id="summaryCharacterCounter">
				</span>
				<form:errors cssClass="error" path="summary"/>
			</span>
			<span class="fieldGroup">
				<form:label path="statementDate" class="fieldLabel"><fmt:message key="reportDateLabel"/></form:label>
				<form:input path="statementDate" class="date"/>
				<form:errors cssClass="error" path="statementDate"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="jurisdiction"><fmt:message key="jurisdictionLabel"/></form:label>
				<form:select path="jurisdiction">
					<jsp:include page="../../../includes/nullOption.jsp"/>
					<c:forEach items="${jurisdictions}" var="jurisdiction" varStatus="status">
						<c:choose>
							<c:when test="${incidentStatementForm.jurisdiction eq jurisdiction}">
								<form:option value="${jurisdiction.id}" selected="true"><c:out value="${jurisdiction.organization.name}"/></form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${jurisdiction.id}"><c:out value="${jurisdiction.organization.name}"/></form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<form:errors cssClass="error" path="jurisdiction"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="incidentSceneLabel"/></legend>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="facilityScene"><fmt:message key="facilitySceneLabel"/></form:label>
				<form:checkbox path="facilityScene" id="facilityScene"/>
				<form:errors cssClass="error" path="facilityScene"/>
			</span>
			<c:choose>
				<c:when test="${incidentStatementForm.facilityScene}">
					<c:set var="facilityContainerDisplayClass" value=""/>
				</c:when>
				<c:otherwise>
					<c:set var="facilityContainerDisplayClass" value="hidden"/>
				</c:otherwise>
			</c:choose>
			<span id="facilityContainer" class="${facilityContainerDisplayClass}">
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="facility"><fmt:message key="facilityLabel"/></form:label>
					<form:select path="facility">
						<jsp:include page="../../../includes/nullOption.jsp"/>
						<form:options items="${facilities}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:errors cssClass="error" path="facility"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="complex"><fmt:message key="complexLabel"/></form:label>
					<form:select path="complex" >
						<jsp:include page="../../../includes/nullOption.jsp"/>
						<form:options items="${complexes}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:errors cssClass="error" path="complex"/>
				</span>
				<span class="fieldGroup">
					<form:label class="fieldLabel" path="housingScene"><fmt:message key="housingSceneLabel"/></form:label>
					<form:checkbox path="housingScene" id="housingScene"/>
					<form:errors cssClass="error" path="housingScene"/>
				</span>
				<c:choose>
					<c:when test="${incidentStatementForm.housingScene}">
						<c:set var="nonHousingContainerDisplayClass" value="hidden"/>
						<c:set var="housingContainerDisplayClass" value=""/>
					</c:when>
					<c:otherwise>
						<c:set var="nonHousingContainerDisplayClass" value=""/>
						<c:set var="housingContainerDisplayClass" value="hidden"/>
					</c:otherwise>
				</c:choose>
				<span id="nonHousingContainer" class="${nonHousingContainerDisplayClass}">
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="facilityArea"><fmt:message key="fieldAreaLabel"/></form:label>
						<form:select path="facilityArea">
							<jsp:include page="../../../includes/nullOption.jsp"/>
							<form:options items="${facilityAreas}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="facilityArea"/>
					</span>
				</span>
				<span id="housingContainer" class="${housingContainerDisplayClass}">
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="unit"><fmt:message key="unitLabel"/></form:label>
						<form:select path="unit">
							<jsp:include page="../../../includes/nullOption.jsp"/>
							<form:options items="${units}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="unit"/>
					</span>
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="section"><fmt:message key="sectionLabel"/></form:label>
						<form:select path="section">
							<jsp:include page="../../../includes/nullOption.jsp"/>
							<form:options items="${sections}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="section"/>
					</span>
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="level"><fmt:message key="levelLabel"/></form:label>
						<form:select path="level">
							<jsp:include page="../../../includes/nullOption.jsp"/>
							<form:options items="${levels}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="level"/>
					</span>
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="room"><fmt:message key="roomLabel"/></form:label>
						<form:select path="room">
							<jsp:include page="../../../includes/nullOption.jsp"/>
							<form:options items="${rooms}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="room"/>
					</span>
				</span>
			</span>
			<span class="fieldGroup">
				<form:label path="location" class="fieldLabel"><fmt:message key="locationLabel"/></form:label>
				<form:textarea path="location" maxlength="4000"/>
				<span class="characterCounter" id="locationCharacterCounter">
             	</span>
				<form:errors cssClass="error" path="location"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="involvedPartiesLabel"/></legend>
			<c:set var="involvedPartyItems" value="${incidentStatementForm.involvedPartyItems}" scope="request"/>
			<jsp:include page="involvedPartyItemsTable.jsp"/>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="sourceAndDocumentationLabel"/></legend>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="confidentialInformant"><fmt:message key="confidentialInformantLabel"/></form:label>
				<form:checkbox path="confidentialInformant" id="confidentialInformant"/>
				<form:errors cssClass="error" path="confidentialInformant"/>
			</span>
			<span class="fieldGroup">
				<form:label path="reporter" class="fieldLabel"><fmt:message key="informationSourceLabel"/></form:label>
				<c:forEach items="${informationSourceCategories}" var="category" varStatus="status">
					<form:radiobutton path="informationSourceCategory" value="${category}" class="informationSourceCategoryRadioButton"/>
					<form:label path="informationSourceCategory" class="fieldValueLabel"><fmt:message key="informationSourceCategory.${category}.radioButtonLabel"/></form:label>
				</c:forEach>
				<form:errors cssClass="error" path="informationSourceCategory"/>
			</span>
			<span class="fieldGroup" id="informantFieldGroup">
				<jsp:include page="informantSearch.jsp"/>
			</span>
			<span class="fieldGroup">
				<form:label path="reporter" class="fieldLabel"><fmt:message key="reporterLabel"/></form:label>
				<form:input type="hidden" path="reporter"/>
				<c:choose>
					<c:when test='${not empty submissionDisabledAttribute and submissionDisabledAttribute eq "true"}'>
						<input type="text" id="reporterInput" readonly="readonly"/>
					</c:when>
					<c:otherwise>
						<input type="text" id="reporterInput"/>
					</c:otherwise>
				</c:choose>
				<a id="reporterCurrent" class="currentUserAccountLink"></a>
				<a id="reporterClear" class="clearLink"></a>
				<span id="reporterDisplay">
					<c:if test="${not empty incidentStatementForm.reporter}">
						<fmt:message key="reporterInformation">
							<fmt:param value="${incidentStatementForm.reporter.name.lastName}"/>
							<fmt:param value="${incidentStatementForm.reporter.name.firstName}"/>
						</fmt:message>
					</c:if>
				</span>
				<form:errors cssClass="error" path="reporter"/>
				<form:input type="hidden" path="documenter"/>
				<form:errors cssClass="error" path="documenter"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="incidentStatementNotesLabel"/></legend>
			<c:set var="incidentStatementNoteItems" value="${incidentStatementForm.incidentStatementNoteItems}" scope="request"/>
			<jsp:include page="incidentStatementNoteItemsTable.jsp"/>
			<form:input type="hidden" path="submissionCategory" value="${incidentStatementForm.submissionCategory}"/>
		</fieldset>
		<c:if test="${not empty incidentStatement}">
			<c:set var="updatable" value="${incidentStatement}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel'/>"/>
		</p>
		<%-- <c:forEach items="${submissionCategories}" var="submissionCategory" >
			<p class="buttons">
				<input type="submit" class="saveAndSubmitButton" name="operation" id="${submissionCategory}" value="<fmt:message key='save.${submissionCategory}.label'/>"/>
			</p>
		</c:forEach> --%>
	</form:form>
</fmt:bundle>