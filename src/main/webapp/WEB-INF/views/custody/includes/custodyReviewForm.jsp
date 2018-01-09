<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.custody.msgs.custodyReview">
<form:form commandName="custodyReviewForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="custodyDetailsLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="changeReason" class="fieldLabel"><fmt:message key="custodyReviewChangeReasonLabel"/></form:label>
			<form:select path="changeReason">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${changeReasons}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="changeReason"/>
		</span>
		<span class="fieldGroup">
			<form:label path="custodyLevel" class="fieldLabel"><fmt:message key="custodyLevelLabel"/></form:label>
			<form:select path="custodyLevel">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<form:options items="${levels}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="custodyLevel"/>
		</span>
		<span class="fieldGroup">
			<form:label path="actionDate" class="fieldLabel"><fmt:message key="custodyReviewActionDateLabel"/></form:label>
			<form:input path="actionDate" class="date"/>
			<form:errors cssClass="error" path="actionDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="nextReviewDate" class="fieldLabel"><fmt:message key="custodyReviewNextReviewDateLabel"/></form:label>
			<form:input path="nextReviewDate" class="date"/>
			<form:errors cssClass="error" path="nextReviewDate"/>
		</span>
	</fieldset>
		<c:if test="${not empty custodyReview}">
			<c:set var="updatable" value="${custodyReview}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='custodyReviewSaveLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>