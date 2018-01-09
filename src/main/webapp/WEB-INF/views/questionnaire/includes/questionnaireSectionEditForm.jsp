<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editQuestionnaire" access="hasRole('QUESTIONNAIRE_CREATE') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<form:form commandName="questionnaireSectionForm" class="editForm">
<fieldset>
	<legend>
		<fmt:message key="questionnaireSectionDetailsTitle" />
	</legend>
	<span class="fieldGroup">
		<form:label path="title" class="fieldLabel">
			<fmt:message key="titleLabel"/>
		</form:label>
		<form:input path="title" />
		<form:errors path="title" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="sectionNumber" class="fieldLabel">
			<fmt:message key="sectionNumberLabel"/>
		</form:label>
		<form:input path="sectionNumber" />
		<form:errors path="sectionNumber" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="sectionType" class="fieldLabel">
			<fmt:message key="sectionType"/>
		</form:label>
		<form:select path="sectionType">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach items="${sectionTypes}" var="type">
				<c:set var="typeId" value="${type.id}"/>
				<option value="${typeId}" ${typeId == questionnaireSectionForm.sectionType.id ? 'selected="selected"' : ''}>
					<c:out value="${type.description}"/>
				</option>
			</c:forEach>
		</form:select>
		<form:errors path="sectionType" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="sectionHelp" class="fieldLabel">
			<fmt:message key="helpTextLabel"/>
		</form:label>
		<form:input path="sectionHelp" />
	</span>
</fieldset>

	<c:if test="${not empty questionnaireSection}">
		<c:set var="updatable" value="${questionnaireSection}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editQuestionnaire}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>