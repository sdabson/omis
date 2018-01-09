<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionList.html?questionnaireType=${questionnaireSection.questionnaireType.id}">
					<span class="visibleLinkLabel"><fmt:message key="listSectionsLink"/></span>
				</a>
			</li>
			<c:if test="${administeredQuestionnaireCount eq 0}">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/questionAnswerEdit.html?questionnaireSection=${questionnaireSection.id}">
					<span class="visibleLinkLabel"><fmt:message key="editQuestionsLink" /></span></a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>