<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editQuestionnaire" access="hasRole('QUESTIONNAIRE_CREATE') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<form:form commandName="questionnaireForm" class="editForm">
<fieldset>
	
	<span class="fieldGroup">
		<form:label path="assessor" class="fieldLabel">
			<fmt:message key="assessorLabel" />
		</form:label>
		
		<c:choose>
			<c:when test="${not empty assessorQuery}"><c:set var="assessorQuery" value="${assessorQuery}"/></c:when>
			<c:when test="${not empty questionnaireForm.assessor}"><c:set var="assessorQuery"><c:set var="userAccount" value="${questionnaireForm.assessor}" scope="request"/>
				<jsp:include page="/WEB-INF/views/user/includes/userAccount.jsp"/></c:set>
			</c:when>
		</c:choose>
		<input type="text" name="assessorQuery" id="assessorQuery" value="<c:out value='${assessorQuery}'/>"/>
		<input type="hidden" name="assessor" id="assessor" value="<c:out value='${questionnaireForm.assessor.id}'/>"/>
		<a id="clearAssessorLink" class="clearLink" title="<fmt:message key='clearAssessorLink' />" href="${pageContext.request.contextPath}/questionnaire/clearAssessor.html"></a>
		<a id="useCurrentUserAccountLink" class="currentUserAccountLink" title="<fmt:message key='useCurrentUserAccountAsAuthorizedByUserLink' />" href="${pageContext.request.contextPath}/questionnaire/useCurrentUserAccountAsAssessor.html"></a>
		<form:errors path="assessor" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="comments" class="fieldLabel">
			<fmt:message key="commentsLabel" />
		</form:label>
		<form:textarea path="comments" maxLength="1024" class="textValue"/>
		<form:errors path="comments" cssClass="error"/>
	</span>
</fieldset>

	<c:if test="${editQuestionnaire}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>