<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<c:if test="${administeredQuestionnaireCount eq 0}">
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionEdit.html?questionnaireSection=${questionnaireSection.id}">
				<span class="visibleLinkLabel"><fmt:message key="editQuestionnaireSectionLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/questionAnswerEdit.html?questionnaireSection=${questionnaireSection.id}">
				<span class="visibleLinkLabel"><fmt:message key="editQuestionsLink" /></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('QUESTIONNAIRE_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionRemove.html?questionnaireSection=${questionnaireSection.id}">
				<span class="visibleLinkLabel"><fmt:message key="removeSectionLink" /></span></a>
			</li>
		</sec:authorize>
		</c:if>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/questionnaire/questionList.html?questionnaireSection=${questionnaireSection.id}">
				<span class="visibleLinkLabel"><fmt:message key="listQuestionsLink" /></span></a>
			</li>
		</sec:authorize>
		
	</ul>
</fmt:bundle>