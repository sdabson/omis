<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.incident.msgs.incident">
	<form:form commandName="searchIncidentStatementForm" class="searchForm" action="searchResults.html" method="GET">
		<fieldset>
			<legend><fmt:message key="searchIncidentStatementsFieldsetLegend"/></legend>
			<span class="fieldGroup">
				<form:label path="title" class="fieldLabel"><fmt:message key="titleSearchLabel"/></form:label>
				<form:input path="title" type="text" size="50"/>
				<form:errors cssClass="error" path="title"/>
			</span>
			<span class="fieldGroup">
				<form:label path="reporter" class="fieldLabel"><fmt:message key="reporterSearchLabel"/></form:label>
				<form:input type="hidden" path="reporter"/>
				<input type="text" id="reporterInput"/>
				<a id="reporterCurrent" class="currentUserAccountLink"></a>
				<a id="reporterClear" class="clearLink"></a>
				<span id="reporterDisplay">
					<c:if test="${not empty searchIncidentStatementForm.reporter}">
						<fmt:message key="reporterInformation">
							<fmt:param value="${searchIncidentStatementForm.reporter.name.lastName}"/>
							<fmt:param value="${searchIncidentStatementForm.reporter.name.firstName}"/>
						</fmt:message>
					</c:if>
				</span>
				<form:errors cssClass="error" path="reporter"/>
			</span>
			<span class="fieldGroup">
				<form:label path="keywords" class="fieldLabel"><fmt:message key="keywordsSearchLabel"/></form:label>
				<form:input path="keywords" size="100" type="text"/>
				<form:errors cssClass="error" path="keywords"/>
			</span>
			<span class="fieldGroup">
				<form:label path="category" class="fieldLabel"><fmt:message key="categorySearchLabel"/></form:label>
				<form:select path="category" >
					<jsp:include page="../../../includes/nullOption.jsp"/>
					<form:options items="${categories}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="category"/>
			</span>
			<span class="fieldGroup">
				<form:label path="jurisdiction" class="fieldLabel"><fmt:message key="jurisdictionSearchLabel"/></form:label>
				<form:select path="jurisdiction" >
					<jsp:include page="../../../includes/nullOption.jsp"/>
					<form:options items="${jurisdictions}" itemLabel="organization.name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="jurisdiction"/>
			</span>
			<span class="fieldGroup">
				<form:label path="startDate" class="fieldLabel"><fmt:message key="startDateSearchLabel"/></form:label>
				<form:input path="startDate" type="text" class="date"/>
				<form:errors cssClass="error" path="startDate"/>
			</span>
			<span class="fieldGroup">
				<form:label path="endDate" class="fieldLabel"><fmt:message key="endDateSearchLabel"/></form:label>
				<form:input path="endDate" type="text" class="date"/>
				<form:errors cssClass="error" path="endDate"/>
			</span>
			<c:choose>
				<c:when test="${searchIncidentStatementForm.involvedPartyOption eq  'OFFENDER'}">
					<c:set value="offenderInvolvedParty" var="involvedPartyDisplayClass"/>
				</c:when>
				<c:when test="${searchIncidentStatementForm.involvedPartyOption eq  'STAFF'}">
					<c:set value="staffInvolvedParty" var="involvedPartyDisplayClass"/>
				</c:when>
				<c:when test="${searchIncidentStatementForm.involvedPartyOption eq  'OTHER'}">
					<c:set value="otherInvolvedParty" var="involvedPartyDisplayClass"/>
				</c:when>
				<c:otherwise>
					<c:set value="" var="involvedPartyDisplayClass"/>
				</c:otherwise>
			</c:choose>
			<span class="fieldGroup">
				<form:label path="involvedParty" class="fieldLabel"><fmt:message key="involvedPartySearchLabel"/></form:label>
				<c:forEach items="${involvedPartyOptions}" var="option">
					<form:radiobutton path="involvedPartyOption" value="${option.name}" id="involvedPartyOption${optionName}"/>
					<label><fmt:message key="involvedPartyOption.${option.name}.SearchLabel"/></label>
				</c:forEach>
			</span>
			<span class="fieldGroup" id="involvedPartyFieldGroup">
				<form:label path="involvedParty" class="fieldLabel"></form:label>
				<c:choose>
					<c:when test="${searchIncidentStatementForm.involvedPartyOption eq  'OFFENDER'}">
						<input type="text" size="50" id="offenderInvolvedPartyInput" class="offenderSearchInput"/>
						<form:input type="hidden" path="involvedParty"/>
						<form:errors cssClass="error" path="involvedParty"/>
						<a id="offenderClear" class="clearLink"></a>
						<span id="involvedPartyDisplay">
							<c:if test="${not empty searchIncidentStatementForm.involvedParty}">
								<fmt:message key="involvedPartySearchInformation">
									<fmt:param value="${searchIncidentStatementForm.involvedParty.name.lastName}"/>
									<fmt:param value="${searchIncidentStatementForm.involvedParty.name.firstName}"/>
								</fmt:message>
							</c:if>
						</span>
					</c:when>
					<c:when test="${searchIncidentStatementForm.involvedPartyOption eq  'STAFF'}">
						<input type="text" size="50" id="staffInvolvedPartyInput" class="staffSearchInput"/>
						<form:input type="hidden" path="involvedParty"/>
						<form:errors cssClass="error" path="involvedParty"/>
						<a id="staffCurrent" class="currentUserAccountLink"></a>
						<a id="staffClear" class="clearLink"></a>
						<span id="involvedPartyDisplay">
							<c:if test="${not empty searchIncidentStatementForm.involvedParty}">
								<fmt:message key="involvedPartySearchInformation">
									<fmt:param value="${searchIncidentStatementForm.involvedParty.name.lastName}"/>
									<fmt:param value="${searchIncidentStatementForm.involvedParty.name.firstName}"/>
								</fmt:message>
							</c:if>
						</span>
					</c:when>
					<c:when test="${searchIncidentStatementForm.involvedPartyOption eq  'OTHER'}">
						<form:input type="text" size="50" path="involvedPartyName"/>
					</c:when>
				</c:choose>
				<form:errors cssClass="error" path="involvedPartyName"/>
			</span>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='searchButtonLabel'/>"/>
			</p>
		</fieldset>
	</form:form>
</fmt:bundle>