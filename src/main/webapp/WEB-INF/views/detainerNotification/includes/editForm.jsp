<%@page import="omis.detainernotification.domain.DetainerWarrantCancellationReason"%>
<%@page import="omis.detainernotification.domain.DetainerJurisdictionCategory"%>
<%@page import="omis.detainernotification.domain.InterstateAgreementInitiatedByCategory"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editDetainerNotification" access="hasRole('DETAINER_NOTIFICATION_CREATE') or hasRole('DETAINER_NOTIFICATION_EDIT') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
<c:set var="detainerWarrantProcessingStatusFields" value="${detainerNotificationForm.detainerWarrantProcessingStatusFields}" scope="request"/>
<c:set var="addressFields" value="${detainerNotificationForm.addressFields}" scope="request"/>
<c:set var="addressFieldsPropertyName" value="addressFields" scope="request"/>
<c:set var="form" value="${detainerNotificationForm}" scope="request"/>
<form:form commandName="detainerNotificationForm" class="editForm" enctype="multipart/form-data">
	<fieldset>
		<legend><fmt:message key="detainerNotificationTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="receiveDate" class="fieldLabel">
				<fmt:message key="receivedDateLabel"/>
			</form:label>
			<form:input path="receiveDate" class="date"/>
			<form:errors path="receiveDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="issueDate" class="fieldLabel">
				<fmt:message key="issuedDateLabel"/>
			</form:label>
			<form:input path="issueDate" class="date"/>
			<form:errors path="issueDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="detainerType" class="fieldLabel">
				<fmt:message key="detainerTypeLabel"/>
			</form:label>
			<form:select path="detainerType">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${detainerType}" var="detainerType">
					<c:set var="detainerTypeId" value="${detainerType.id}"/>
					<option value="${detainerTypeId}" ${detainerTypeId == detainerNotificationForm.detainerType.id ? 'selected="selected"' : ''}>
						<c:out value="${detainerType.name}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="detainerType" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="courtCaseNumber" class="fieldLabel">
				<fmt:message key="courtCaseNumberLabel"/>
			</form:label>
			<form:input path="courtCaseNumber"/>
			<form:errors cssClass="error" path="courtCaseNumber"/>
		</span>
		<span class="fieldGroup">
				<form:label path="warrantNumber" class="fieldLabel">
					<fmt:message key="warrantNumberLabel"/>
				</form:label>
				<form:input path="warrantNumber" />
				<form:errors  cssClass="error" path="warrantNumber"/>
		</span>
		<span class="fieldGroup">
			<form:label path="alternateOffenderId" class="fieldLabel">
				<fmt:message key="alternateOffenderIdLabel"/>
			</form:label>
			<form:input path="alternateOffenderId"/>
		</span>
		<span class="fieldGroup">
			<form:label path="jurisdiction" class="fieldLabel">
				<fmt:message key="jurisdictionLabel"/>
			</form:label>
			<form:select path="jurisdiction">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${jurisdictionValues}" var="jurisdictionValue">
					<option value="${jurisdictionValue}" ${jurisdictionValue == detainerNotificationForm.jurisdiction ? 'selected="selected"' : ''}>
						<fmt:message key="jurisdiction${jurisdictionValue}Label"/>
					</option>
				</c:forEach>
			</form:select>
		</span>
		<span class="fieldGroup">
			<form:label path="offenseDescription" class="fieldLabel">
				<fmt:message key="offenseDescriptionLabel"/>
			</form:label>
			<form:textarea path="offenseDescription" maxlength="2048" class="countableCharacters"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="detainerAgencyLegendLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="detainerAgency" class="fieldLabel">
				<fmt:message key="detainerAgencyLabel"/>
			</form:label>
			<form:select path="detainerAgency" id="detainerAgencySelect">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${detainerAgency}" var="detainerAgency">
					<c:set var="detainerAgencyId" value="${detainerAgency.id}"/>
					<option value="${detainerAgencyId}" ${detainerAgencyId == detainerNotificationForm.detainerAgency.id ? 'selected="selected"' : ''}>
						<c:out value="${detainerAgency.agencyName}"/>
					</option>
				</c:forEach>
			</form:select>
			<c:choose>
				<c:when test="${creatingDetainerAgency}">
					<c:set var="creatingDetainerAgency" value="true" />
				</c:when>
				<c:otherwise>
					<c:set var="creatingDetainerAgency" value="false" />
				</c:otherwise>
			</c:choose>
			<form:input type="hidden" path="creatingDetainerAgency" 
				name="creatingDetainerAgency" 
				id="creatingDetainerAgency" value="${creatingDetainerAgency}" />
			<label for="creatingDetainerAgency" class="createDetainerAgency" 
				id="createDetainerAgency">
				<img src="../resources/common/images/add.png" />
			</label>
		</span>
		<span id="detainerAgencyDetails" class="fieldGroup"></span>
		<div class="hidden" id="daTbl">
				<span class="fieldGroup">
					<form:label path="agencyName" class="fieldLabel">
						<fmt:message key="agencyNameLabel"/>
					</form:label>
					<form:input path="agencyName" />
					<form:errors path="agencyName" cssClass="error"/>
				</span>
				<span class="fieldGroup">
					<form:label path="telephoneNumber" class="fieldLabel">
							<fmt:message key="telephoneNumberLabel"/>
						</form:label>
						<form:input path="telephoneNumber" />
						<form:errors path="telephoneNumber" cssClass="error"/>
				</span>
			<span class="fieldGroup">
				<form:label path="usingAddress" class="fieldLabel">
					<fmt:message key="usingAddressLabel"/>
				</form:label>
				<form:checkbox id="usingAddress" path="usingAddress" />
			</span>
			<jsp:include page="../../address/includes/addressFields.jsp"/>
		</div>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="detainerProcessingStatusLegendLabel"/></legend>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="processed"><fmt:message key="processedLabel"/></form:label>
			<form:checkbox path="processed" value="true" id="processed"/>
		</span>
		<c:choose>
			<c:when test="${form.processed}">
				<c:set value="" var="processingStatusDisplayClass"/>
			</c:when>
			<c:otherwise>
				<c:set value="hidden" var="processingStatusDisplayClass"/>
			</c:otherwise>
		</c:choose>
		<span id="processingContainer" class="${processingStatusDisplayClass}">
			<span class="fieldGroup">
					<form:label path="detainerWarrantProcessingStatusFields.otherFacility" class="fieldLabel">
						<fmt:message key="otherFacilityLabel"/>
					</form:label>
					<form:radiobutton path="detainerWarrantProcessingStatusFields.otherFacility" value="false" id="otherFacilityFalse"/>
					<label class="fieldValueLabel"><fmt:message key="otherFacilityFalseLabel"/></label>
					<form:radiobutton path="detainerWarrantProcessingStatusFields.otherFacility" value="true" id="otherFacilityTrue"/>
					<label class="fieldValueLabel"><fmt:message key="otherFacilityTrueLabel"/></label>
				</span>
				<c:choose>
					<c:when test="${detainerWarrantProcessingStatusFields.otherFacility}">
						<c:set var="facilityContainerDisplayClass" scope="page" value="hidden"/>
						<c:set var="facilityNameContainerDisplayClass" scope="page" value=""/>
					</c:when>
					<c:otherwise>
						<c:set var="facilityDisplayClass" scope="page" value=""/>
						<c:set var="facilityNameContainerDisplayClass" scope="page" value="hidden"/>
					</c:otherwise>
				</c:choose>
				<span id="facilityNameContainer" class="fieldGroup ${facilityNameContainerDisplayClass}">
					<form:label path="detainerWarrantProcessingStatusFields.facilityName" class="fieldLabel">
						<fmt:message key="facilityNameLabel"/>
					</form:label>
					<form:input type="text" path="detainerWarrantProcessingStatusFields.facilityName" id="facilityName" maxlength="128"/>
					<form:errors path="detainerWarrantProcessingStatusFields.facilityName" cssClass="error"/>
				</span>
				<span id="facilityContainer" class="${facilityContainerDisplayClass}">
					<span class="fieldGroup">
					<form:label path="detainerWarrantProcessingStatusFields.facility" class="fieldLabel">
							<fmt:message key="facilityLabel"/>
						</form:label>
						<form:select path="detainerWarrantProcessingStatusFields.facility" id="facility">
							<jsp:include page="../../includes/nullOption.jsp"/>
							<c:forEach items="${facilities}" var="facility">
								<option value="${facility.id}" ${facility == detainerWarrantProcessingStatusFields.facility ? 'selected="selected"' : ''}>
									<c:out value="${facility.name}"/>
								</option>
							</c:forEach>
						</form:select>
						<form:errors path="detainerWarrantProcessingStatusFields.facility" cssClass="error"/>
					</span>
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="detainerWarrantProcessingStatusFields.complex"><fmt:message key="complexLabel"/></form:label>
						<form:select path="detainerWarrantProcessingStatusFields.complex" id="complex">
							<jsp:include page="../../includes/nullOption.jsp"/>
							<form:options items="${complexes}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="detainerWarrantProcessingStatusFields.complex"/>
					</span>
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="detainerWarrantProcessingStatusFields.unit"><fmt:message key="unitLabel"/></form:label>
						<form:select path="detainerWarrantProcessingStatusFields.unit" id="unit">
							<jsp:include page="../../includes/nullOption.jsp"/>
							<form:options items="${units}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="detainerWarrantProcessingStatusFields.unit"/>
					</span>
				</span>
					<span class="fieldGroup">
						<form:label path="detainerWarrantProcessingStatusFields.sentToFacilityDate" class="fieldLabel">
								<fmt:message key="sentToFacilityDateLabel"/>
							</form:label>
							<form:input path="detainerWarrantProcessingStatusFields.sentToFacilityDate" class="date"/>
					</span>
					<span class="fieldGroup">
						<form:label path="detainerWarrantProcessingStatusFields.inmateServedDate" class="fieldLabel">
								<fmt:message key="inmateServedDateLabel"/>
							</form:label>
							<form:input path="detainerWarrantProcessingStatusFields.inmateServedDate" class="date"/>
					</span>
					<span class="fieldGroup">
						<form:label path="detainerWarrantProcessingStatusFields.cancellation.reason" class="fieldLabel">
								<fmt:message key="cancellationReasonLabel"/>
							</form:label>
							<form:select path="detainerWarrantProcessingStatusFields.cancellation.reason">
								<jsp:include page="../../includes/nullOption.jsp"/>
								<c:forEach items="${cancellationReasonValues}" var="cancellationReason">
									<option value="${cancellationReason}" ${cancellationReason == detainerWarrantProcessingStatusFields.cancellation.reason ? 'selected="selected"' : ''}>
										<fmt:message key="cancellationReason${cancellationReason}Label"/>
									</option>
								</c:forEach>
							</form:select>
					</span>
					<span class="fieldGroup">
						<form:label path="detainerWarrantProcessingStatusFields.cancellation.date" class="fieldLabel">
							<fmt:message key="cancellationDateLabel"/>
						</form:label>
						<form:input path="detainerWarrantProcessingStatusFields.cancellation.date" class="date" />
					</span>
				<span class="fieldGroup">
					<form:label path="detainerWarrantProcessingStatusFields.refusedToSign" class="fieldLabel">
						<fmt:message key="refusedToSignLabel"/>
					</form:label>
					<form:checkbox path="detainerWarrantProcessingStatusFields.refusedToSign" id="refusedToSign" />
				</span>
				<c:choose>
					<c:when test="${detainerWarrantProcessingStatusFields.refusedToSign}">
						<c:set var="refusedToSignDisplayClass" value=""/>
					</c:when>
					<c:otherwise>
						<c:set var="refusedToSignDisplayClass" value="hidden"/>
					</c:otherwise>
				</c:choose>
				<span id="refusedToSignCommentContainer" class="fieldGroup ${refusedToSignDisplayClass}">
					<form:label path="detainerWarrantProcessingStatusFields.refusedToSignComment" class="fieldLabel">
						<fmt:message key="commentsLabel"/>
					</form:label>
					<form:textarea path="detainerWarrantProcessingStatusFields.refusedToSignComment" id="refusedToSignComment" maxlength="2048" class="countableCharacters" />
				</span>
				<span class="fieldGroup">
					<form:label path="detainerWarrantProcessingStatusFields.waiverRequired" class="fieldLabel">
						<fmt:message key="waiverRequiredLabel"/>
					</form:label>
					<form:checkbox path="detainerWarrantProcessingStatusFields.waiverRequired" id="waiverRequired" />
				</span>
				<c:choose>
					<c:when test="${detainerWarrantProcessingStatusFields.waiverRequired}">
						<c:set var="waiverRequiredDisplayClass" value=""/>
					</c:when>
					<c:otherwise>
						<c:set var="waiverRequiredDisplayClass" value="hidden"/>
					</c:otherwise>
				</c:choose>
				<span id="waiverRequiredCommentContainer" class="fieldGroup ${waiverRequiredDisplayClass}">
					<form:label path="detainerWarrantProcessingStatusFields.waiverRequiredComment"  class="fieldLabel">
						<fmt:message key="commentsLabel"/>
					</form:label>
					<form:textarea path="detainerWarrantProcessingStatusFields.waiverRequiredComment" id="waiverRequiredComment" maxlength="2048" class="countableCharacters" />
				</span>
			</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="interstateAgreementLegendLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="usingInterstateAgreementDetainer" class="fieldLabel">
				<fmt:message key="interstateAgreementDetainerLabel"/>
			</form:label>
			<form:checkbox id="usingInterstateAgreementDetainer" path="usingInterstateAgreementDetainer" />
		</span>
		<div class="hidden" id="iasTbl">
			<span class="fieldGroup">
				<form:label path="initiatedBy" class="fieldLabel">
						<fmt:message key="initiatedByLabel"/>
					</form:label>
					<form:select path="initiatedBy">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach items="${initiatedByValues}" var="initiatedByValue">
						<option value="${initiatedByValue}" ${initiatedByValue == detainerNotificationForm.initiatedBy ? 'selected="selected"' : ''}>
							<fmt:message key="initiatedBy${initiatedByValue.name}Label"/>
						</option>
					</c:forEach>
				</form:select>
				<form:errors path="initiatedBy" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="prosecutorReceivedDate" class="fieldLabel">
					<fmt:message key="prosecutorReceivedDateLabel"/>
				</form:label>
				<form:input path="prosecutorReceivedDate" class="date"/>
			</span>
			<span class="fieldGroup">
				<c:set var="interstateDetainerActivityItems" value="${detainerNotificationForm.interstateDetainerActivityItems}" scope="request"/>
				<jsp:include page="interstateDetainerActivityItems.jsp"/>
			</span>
		</div>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="detainerNotesLegendLabel"/></legend>
		<c:set var="noteItems" value="${detainerNotificationForm.noteItems}" scope="request"/>
		<jsp:include page="noteItemsTable.jsp"/>
	</fieldset>
	<c:if test="${not empty detainer}">
		<c:set var="updatable" value="${detainer}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editDetainerNotification}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>