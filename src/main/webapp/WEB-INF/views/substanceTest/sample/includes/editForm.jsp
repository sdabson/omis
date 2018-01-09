<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<form:form commandName="substanceTestSampleForm" class="editForm">
		<fieldset>
			<c:set var="substanceTestSampleForm" value="${substanceTestSampleForm}" scope="request"/>
			<legend><fmt:message key="sampleCollectionLegendLabel"/></legend>
			<form:input path="random" type="hidden"/>
			<c:choose>
				<c:when test="${not empty substanceTestSampleForm.request}">
					<c:choose>
						<c:when test="${substanceTestSampleForm.taken and not empty substanceTestSampleForm.taken}">
							<c:set var="statusReasonDisplayClass" value="fieldGroup hidden"/>
							<c:set var="sampleDetailsDisplayClass" value=""/>
						</c:when>
						<c:when test="${not substanceTestSampleForm.taken and not empty substanceTestSampleForm.taken}">
							<c:set var="statusReasonDisplayClass" value="fieldGroup"/>
							<c:set var="sampleDetailsDisplayClass" value="hidden"/>
						</c:when>
						<c:when test="${empty substanceTestSampleForm.taken}">
							<c:set var="statusReasonDisplayClass" value="fieldGroup hidden"/>
							<c:set var="sampleDetailsDisplayClass" value="hidden"/>
						</c:when>
					</c:choose>
					<c:set var="takenDisplayClass" value="fieldGroup"/>
				</c:when>
				<c:otherwise>
					<c:set var="statusReasonDisplayClass" value="fieldGroup hidden"/>
					<c:set var="sampleDetailsDisplayClass" value=""/>
					<c:set var="takenDisplayClass" value="fieldGroup hidden"/>
				</c:otherwise>
			</c:choose>
			<span class="${takenDisplayClass}">
				<form:label path="taken" class="fieldLabel"><fmt:message key="takenLabel"/></form:label>
				<label class="fieldValueLabel"><fmt:message key="takenTrueLabel"/></label>
				<form:radiobutton path="taken" id="takenTrue" value="true"/>
				<label class="fieldValueLabel"><fmt:message key="takenFalseLabel"/></label>
				<form:radiobutton path="taken" id="takenFalse" value="false"/>
				<form:errors cssClass="error" path="taken"/>
			</span>
			<span class="${statusReasonDisplayClass}" id="sampleStatusContent">
				<form:label path="statusReason" class="fieldLabel"><fmt:message key="sampleStatusReasonLabel"/></form:label>
				<form:select path="statusReason">
					<jsp:include page="sampleStatusReasonOptions.jsp"/>
				</form:select>
				<form:errors cssClass="error" path="statusReason"/>
			</span>
			<span id="sampleDetailsContainer" class="${sampleDetailsDisplayClass}">
				<c:choose>
					<c:when test="${substanceTestSampleForm.random}">
							<span class="fieldGroup">
								<form:label path="substanceTestReason" class="fieldLabel"><fmt:message key="substanceTestReasonLabel"/></form:label>
								<input type="text" value="<fmt:message key="randomLabel"/>"  disabled="disabled"/>
							</span>
					</c:when>
					<c:otherwise>
							<span class="fieldGroup">
								<form:label path="substanceTestReason" class="fieldLabel"><fmt:message key="substanceTestReasonLabel"/></form:label>
								<form:select path="substanceTestReason">
								<form:option value="">...</form:option>
								<form:options items="${substanceTestReasons}" itemLabel="name" itemValue="id"/>
								</form:select>
								<form:errors cssClass="error" path="substanceTestReason"/>
							</span>
					</c:otherwise>
				</c:choose>
				<span class="fieldGroup">
					<form:label path="collectionDate" class="fieldLabel"><fmt:message key="collectionDateLabel"/></form:label>
					<form:input path="collectionDate" class="date"/>
					<form:errors cssClass="error" path="collectionDate"/>
				</span>
				<span class="fieldGroup">
					<form:label path="collectionTime" class="fieldLabel"><fmt:message key="collectionTimeLabel"/></form:label>
					<form:input path="collectionTime" class="time"/>
					<form:errors path="collectionTime" cssClass="error"/>
				</span>
				<span class="fieldGroup">
					<form:label path="collectionEmployee" class="fieldLabel"><fmt:message key="collectionEmployeeLabel"/></form:label>
					<form:hidden path="collectionEmployee"/>
					<input type="text" id="collectionEmployeeInput"/>
					<a id="collectionEmployeeCurrent" class="currentUserAccountLink"></a>
					<a id="collectionEmployeeClear" class="clearLink"></a>
					<span id="collectionEmployeeDisplay">
						<c:if test="${not empty substanceTestSampleForm.collectionEmployee}">
							<fmt:message key="collectionEmployeeInformation">
								<fmt:param value="${substanceTestSampleForm.collectionEmployee.name.lastName}"/>
								<fmt:param value="${substanceTestSampleForm.collectionEmployee.name.firstName}"/>
							</fmt:message>
						</c:if>
					</span>
					<form:errors cssClass="error" path="collectionEmployee"/>
				</span>
				<span class="fieldGroup">
					<form:label path="sampleCollectionMethod" class="fieldLabel"><fmt:message key="sampleCollectionMethodLabel"/></form:label>
					<form:select path="sampleCollectionMethod">
					<form:option value="">...</form:option>
					<form:options items="${sampleCollectionMethods}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:errors cssClass="error" path="sampleCollectionMethod"/>
				</span> 	
				</span>
		</fieldset>
		<c:if test="${not empty substanceTestSample}">
			<c:set var="updatable" value="${substanceTestSample}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<form:input type="hidden" id="saveAndContinue" path="saveAndContinue"/>
		<c:choose>
			<c:when test="${(substanceTestSampleForm.taken and empty substanceTestSample) or (empty substanceTestSampleForm.taken and empty substanceTestSample and not substanceTestSampleForm.random)}">
				<c:set var="submitAndTestDisplayClass" value=""/>
			</c:when>
			<c:otherwise>
				<c:set var="submitAndTestDisplayClass" value=" hidden"/>
			</c:otherwise>
		</c:choose>
		<div id="submitAndTestContianer" class="${submitAndTestDisplayClass}">
			<p class="buttons">
				<c:if test="${empty substanceTestSample}">
					<input type="submit" id="submitAndContinue" value="<fmt:message key='saveAndContinue'/>"/>
				</c:if>
			</p>
		</div>
		<p class="buttons">
			<input type="submit" id="submit" value="<fmt:message key='substanceTestSampleSaveLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>