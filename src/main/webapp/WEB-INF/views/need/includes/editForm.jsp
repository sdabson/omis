<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.need.msgs.need">
	<form:form commandName="casePlanObjectiveForm" class="editForm">
		<fieldset>
		<legend><fmt:message key="objectiveDetailsLegendLabel"/></legend>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="name"><fmt:message key="nameLabel"/></form:label>
				<form:input path="name"/>
				<form:errors cssClass="error" path="name"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="domain"><fmt:message key="needDomainLabel"/></form:label>
				<form:select path="domain">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<form:options items="${needDomains}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="domain"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="identifiedDate"><fmt:message key="identifiedDateLabel"/></form:label>
				<form:input path="identifiedDate" class="date"/>
				<form:errors cssClass="error" path="identifiedDate"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="offenderAgrees"><fmt:message key="offenderAgreesLabel"/></form:label>
				<form:select path="offenderAgrees">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<form:option value="true"><fmt:message key="yesLabel" /></form:option>
				<form:option value="false"><fmt:message key="noLabel"/></form:option>
				</form:select>
				<form:errors cssClass="error" path="domain"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="source"><fmt:message key="objectiveSourceLabel"/></form:label>
				<form:select path="source">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<form:options items="${sources}" itemLabel="name" itemValue="name"/>
				</form:select>
				<form:errors cssClass="error" path="source"/>
			</span>
			<c:choose>
				<c:when test="${casePlanObjectiveForm.source == 'STAFF'}">
					<c:set var="displayClass" value="fieldGroup"/>
					</c:when>
					<c:otherwise>
						<c:set var="displayClass" value="fieldGroup hidden"/>
				</c:otherwise>
			</c:choose>
			<span class="${displayClass}" id="staffMemberContentArea">
				<form:label path="staffMember" class="fieldLabel"><fmt:message key="staffMemberLabel"/></form:label>
				<form:input type="hidden" path="staffMember" id="staffMember"/>
				<input type="text" id="staffMemberInput"/>
				<a id="staffMemberClear" class="clearLink"></a>
				<span id="staffMemberDisplay">
					<c:if test="${not empty casePlanObjectiveForm.staffMember}">
						<fmt:message key="staffMemberName">
							<fmt:param value="${casePlanObjectiveForm.staffMember.name.lastName}"/>
							<fmt:param value="${casePlanObjectiveForm.staffMember.name.firstName}"/>
						</fmt:message>
					</c:if>
				</span>
				<form:errors cssClass="error" path="staffMember"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="priority"><fmt:message key="objectivePriorityLabel"/></form:label>
				<form:select path="priority">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<form:options items="${objectivePriorities}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors cssClass="error" path="priority"/>
			</span>
			<span class="fieldGroup">
				<form:label class="fieldLabel" path="comment"><fmt:message key="commentLabel"/></form:label>
				<form:textarea path="comment"/>
				<form:errors cssClass="error" path="comment"/>
			</span>
		</fieldset>
		<c:if test="${not empty casePlanObjective}">
			<c:set var="updatable" value="${casePlanObjective}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</form:form>
</fmt:bundle>