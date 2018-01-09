<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.sentence.msgs.sentence">
	<form:form commandName="sentenceForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="concurrent" class="fieldLabel">
				<fmt:message key="concurrentLabel"/></form:label>
			<form:checkbox path="concurrent" id="concurrent"/>
			<form:errors path="concurrent" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="sentenceCategoryLabel"/></form:label>
			<form:select path="category" id="category">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${categories}" itemLabel="name" itemValue="id"/>	
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="lengthClassification" class="fieldLabel">
				<fmt:message key="sentenceLengthClassificationLabel"/></form:label>
			<form:select path="lengthClassification" id="lengthClassification">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach items="${lengthClassifications}" var="lengthClassification">
					<c:choose>
						<c:when test="${not empty sentenceForm.lengthClassification and sentenceForm.lengthClassification eq lengthClassification}">
							<form:option selected="selected" value="${lengthClassification}"><fmt:message key="sentenceLengthClassificationLabel.${lengthClassification}" /></form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${lengthClassification}"><fmt:message key="sentenceLengthClassificationLabel.${lengthClassification}" /></form:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="lengthClassification" cssClass="error"/>
		</span>	
		<span class="fieldGroup">
			<form:label path="jailTimeCredit" class="fieldLabel">
				<fmt:message key="jailTimeCreditLabel"/></form:label>
			<form:input path="jailTimeCredit" id="jailTimeCredit" class="veryShortNumber"/>
			<form:errors path="jailTimeCredit" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="streetTimeCredit" class="fieldLabel">
				<fmt:message key="streetTimeCreditLabel"/></form:label>
			<form:input path="streetTimeCredit" id="streetTimeCredit" class="veryShortNumber"/>
			<form:errors path="streetTimeCredit" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="effectiveDate" class="fieldLabel">
				<fmt:message key="effectiveDateLabel"/></form:label>
			<form:input path="effectiveDate" id="effectiveDate" class="date"/>
			<form:errors path="effectiveDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="pronouncementDate" class="fieldLabel">
				<fmt:message key="pronouncementDateLabel"/></form:label>
			<form:input path="pronouncementDate" id="pronouncementDate" class="date"/>
			<form:errors path="pronouncementDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="turnSelfInDate" class="fieldLabel">
				<fmt:message key="turnSelfInDateLabel"/></form:label>
			<form:input path="turnSelfInDate" id="turnSelfInDate" class="date"/>
			<form:errors path="turnSelfInDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<a href="${pageContext.request.contextPath}/sentence/showCalculation.html?courtCase=${courtCase.id}" type="submit" id="calculateLink">
				<fmt:message key="calculateLabel"/></a>
		</span>
	</fieldset>
	<fieldset id="calculation">
		<jsp:include page="calculation.jsp"/>
	</fieldset>
	<p class="buttons">
		<button type="submit" value="SAVE"><fmt:message key="saveLabel"/></button>
	</p>
	</form:form>
</fmt:bundle>