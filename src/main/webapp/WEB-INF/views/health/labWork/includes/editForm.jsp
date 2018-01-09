<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 - Author: Joel Norris
 - Version: 0.1.0 (May 6, 2014)
 - Since: OMIS 3.0
--%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<form:form commandName="editLabWorkForm" class="editForm">
		<fieldset>
			<input type="hidden" id="facilityId" value="${facility.id}"/>
			<legend><fmt:message key="labWorkOrderLegendLabel"/></legend>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="orderDate"><fmt:message key="labWorkSampleOrderedDateLabel"/></form:label>
				<form:input path="orderDate" id="orderDate" class="date"/>
				<form:errors cssClass="error" path="orderDate"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="byProvider"><fmt:message key="labWorkSampleOrderedByLabel"/></form:label>
				<c:set var="provider" value="${editLabWorkForm.byProvider}" scope="request"/>
				<form:select path="byProvider" id="byProvider">
					<jsp:include page="/WEB-INF/views/health/labWork/includes/orderedByOptions.jsp"/>
				</form:select>
				<form:errors path="byProvider" cssclass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="schedulingNotes"><fmt:message key="schedulingNotesLabel"/></form:label>
				<form:textarea path="schedulingNotes"/>
				<form:errors cssClass="error" path="schedulingNotes"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="labWorkSampleRestrictionsLabel"/></legend>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="noMeds"><fmt:message key="noMedsSampleRestrictionLabel"/></form:label>
				<form:checkbox path="noMeds"/>
				<form:errors cssClass="error" path="noMeds"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="nothingPerOral"><fmt:message key="nothingPerOralSampleRestrictionLabel"/></form:label>
				<form:checkbox path="nothingPerOral"/>
				<form:errors cssClass="error" path="nothingPerOral"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="noLeaky"><fmt:message key="noLeakySampleRestrictionLabel"/></form:label>
				<form:checkbox path="noLeaky"/>
				<form:errors cssClass="error" path="noLeaky"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="labWorkSampleLegendLabel"/></legend>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="sampleDate"><fmt:message key="labWorkDateLabel"/></form:label>
				<form:input path="sampleDate" class="date"/>
				<form:errors cssClass="error" path="sampleDate"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="sampleLab"><fmt:message key="sampleLabLabel"/></form:label>
				<form:select path="sampleLab">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${labs}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="sampleLab"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="sampleNotes"><fmt:message key="sampleNotesLabel"/></form:label>
				<form:textarea path="sampleNotes"/>
				<form:errors cssClass="error" path="sampleNotes"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="sampleTaken"><fmt:message key="sampleTakenLabel"/></form:label>
				<form:checkbox path="sampleTaken"/>
				<form:errors cssClass="error" path="sampleTaken"/>
			</span>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="labWorkResultsLegendLabel"/></legend>
			<jsp:include page="/WEB-INF/views/health/labWork/includes/labWorkResults.jsp"/>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="labWorkCategory"><fmt:message key="labWorkCategoryLabel"/></form:label>
				<form:select path="labWorkCategory">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${labWorkCategories}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="labWorkCategory"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="resultsLab"><fmt:message key="resultsLabLabel"/></form:label>
				<form:select path="resultsLab">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${labs}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="resultsLab"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="resultsDate"><fmt:message key="resultsDateLabel"/></form:label>
				<form:input path="resultsDate" class="date"/>
				<form:errors cssClass="error" path="resultsDate"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="resultsNotes"><fmt:message key="resultsNotesLabel"/></form:label>
				<form:textarea path="resultsNotes"/>
				<form:errors cssClass="error" path="resultsNotes"/>
			</span>
		</fieldset>
		<c:if test="${not empty labWork}">
		<c:set var="updatable" value="${labWork}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
		</p>
	</form:form>
</fmt:bundle>