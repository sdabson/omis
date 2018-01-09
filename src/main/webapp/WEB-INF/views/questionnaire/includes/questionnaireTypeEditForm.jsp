<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editQuestionnaire" access="hasRole('QUESTIONNAIRE_CREATE') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<form:form commandName="questionnaireTypeForm" class="editForm">
<fieldset>
	<legend>
		<fmt:message key="questionnaireTypeDetailsTitle">
			<fmt:param value="${questionnaireType.title}"/>
		</fmt:message>
	</legend>
	<span class="fieldGroup">
		<form:label path="title" class="fieldLabel">
			<fmt:message key="titleLabel"/>
		</form:label>
		<form:input path="title" />
		<form:errors path="title" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="shortTitle" class="fieldLabel">
			<fmt:message key="shortTitleLabel"/>
		</form:label>
		<form:input path="shortTitle" />
	</span>
	<c:if test="${empty questionnaireType}">
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="categoryLabel"/>
			</form:label>
			<form:select path="category" >
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach var="category" items="${questionnaireCategories}"> 
					<option value="${category.id}"
						${questionnaireTypeForm.category.id == category.id ? 'selected="selected"' : ''}>
						<c:out value="${category.description}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
	</c:if>
	<c:if test="${not empty questionnaireType}">
		<form:input type="hidden" path="category" />
	</c:if>
	<span class="fieldGroup">
		<form:label path="cycle" class="fieldLabel">
			<fmt:message key="cycleLabel"/>
		</form:label>
		<form:input path="cycle" class="shortNumber" />
		<form:errors path="cycle" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="startDate" class="fieldLabel">
			<fmt:message key="startDateLabel"/>
		</form:label>
		<form:input path="startDate" class="date"/>
		<form:errors path="startDate" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="endDate" class="fieldLabel">
			<fmt:message key="endDateLabel"/>
		</form:label>
		<form:input path="endDate" class="date"/>
		<form:errors path="endDate" cssClass="error"/>
	</span>
	<span class="fieldGroup"> 	
		<form:label path="questionnaireHelp" class="fieldLabel">
			<fmt:message key="helpTextLabel"/>
		</form:label>
		<form:input path="questionnaireHelp" />
	</span>
	<span class="fieldGroup">
		<form:label path="valid" class="fieldLabel">
			<fmt:message key="validLabel"/>
		</form:label>
		<form:checkbox path="valid" name="valid" />
	</span>
</fieldset>

	<c:if test="${not empty questionnaireType}">
		<c:set var="updatable" value="${questionnaireType}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editQuestionnaire}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>