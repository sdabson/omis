<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<form:form commandName="substanceTestForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="substanceTestDetailsLabel"/></legend>
				<span class="fieldGroup">
					<form:label path="resultDate" class="fieldLabel"><fmt:message key="resultDateLabel"/></form:label>
					<form:input path="resultDate"/>
					<form:errors cssClass="error" path="resultDate"/>
				</span>
				<span class="fieldGroup">
					<form:label path="testComment" class="fieldLabel"><fmt:message key="testCommentLabel"/></form:label>
					<form:textarea path="testComment"/>
					<form:errors cssClass="error" path="testComment"/>
				</span>
		</fieldset>
		<fieldset id="substanceTestResultHolder">
			<legend><fmt:message key="substanceTestResultsTitle"/></legend>
			<jsp:include page="substanceTestResultsContent.jsp"/>
			<form:errors path="resultItems" cssClass="error"/>
		</fieldset>
		<fieldset id="">
			<legend><fmt:message key="labResultsTitle"/></legend>
			<span class="fieldGroup">
				<c:set var="privateLab" value="${substanceTestForm.lab.privateLab}" scope="request"/>
				<form:label path="labInvolved" class="fieldLabel"><fmt:message key="labInvolvedLabel"/></form:label>
				<form:checkbox id="labInvolved" path="labInvolved"/>
				<form:errors cssClass="error" path="labInvolved"/>
			</span>	
			<div id="labResultContainer">
				<c:choose>
					<c:when test="${substanceTestForm.labInvolved}">
							<jsp:include page="labResultsContent.jsp"/>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
		</fieldset>
		<c:if test="${not empty substanceTest}">
			<c:set var="updatable" value="${substanceTest}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='substanceTestSaveLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>