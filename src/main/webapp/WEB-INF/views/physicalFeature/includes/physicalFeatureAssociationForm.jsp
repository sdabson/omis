<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
	<form:form commandName="physicalFeatureAssociationForm" method="post" enctype="multipart/form-data" class="editForm">
		<fieldset>
			<legend><fmt:message key="physicalFeatureDetailsLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="featureClassification" class="fieldLabel"><fmt:message key="limitBodyMarkLabel"/></form:label>
				<form:select path="featureClassification">
					<form:option value="">...</form:option>
					<form:options items="${featureClassifications}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="featureClassification"/>
			</span>
			<c:if test="${not empty physicalFeatureAssociationForm.featureClassification}">
				<c:set value="${physicalFeatureAssociationForm.featureClassification.name}" var="featureClassificationName" scope="request"/>
			</c:if>
			<span class="fieldGroup">
				<form:label path="feature" class="fieldLabel"><fmt:message key="featureLabel"/></form:label>
				<span id="featureContainer">
					<jsp:include page="featureContent.jsp"/>
				</span>
				<form:errors cssClass="error" path="feature"/>
			</span>
			<span class="fieldGroup">
				<form:label path="originationDate" class="fieldLabel"><fmt:message key="offenderPhysicalFeatureOriginationDateLabel"/></form:label>
				<form:input path="originationDate" class="date"/>
				<form:errors cssClass="error" path="originationDate"/>
			</span>
			<span class="fieldGroup">
				<form:label path="description" class="fieldLabel"><fmt:message key="offenderPhysicalFeatureDescription"/></form:label>
				<form:textarea path="description"/>
				<form:errors cssClass="error" path="description"/>
			</span>
		</fieldset>
		<fieldset id="physicalFeaturePhotoHolder">
			<legend><fmt:message key="physicalFeaturePhotoTitle"/></legend>
			<jsp:include page="physicalFeaturePhotoContent.jsp"/>
			<form:errors path="photoItems" cssClass="error"/>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="physicalFeatureAssociationNotesTitle"/></legend>
			<c:set var="physicalFeatureAssociationNoteItems" value="${physicalFeatureAssociationForm.noteItems}" scope="request"/>
			<jsp:include page="physicalFeatureAssociationNoteContent.jsp"/>
			<form:errors path="noteItems" cssClass="error"/>
		</fieldset>
		<c:if test="${not empty physicalFeatureAssociation}">
			<c:set var="updatable" value="${physicalFeatureAssociation}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='offenderPhysicalFeatureSaveLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>