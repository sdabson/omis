<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<c:if test="${administeredQuestionnaireCount eq 0}">
			<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/questionEdit.html?allowedQuestion=${allowedQuestion.id}">
					<span class="visibleLinkLabel"><fmt:message key="questionEditLink"/></span></a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('QUESTIONNAIRE_EDIT') or hasRole('ADMIN')">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/questionnaire/questionRemove.html?allowedQuestion=${allowedQuestion.id}">
					<span class="visibleLinkLabel"><fmt:message key="removeQuestionLink" /></span></a>
				</li>
			</sec:authorize>
		</c:if>
	</ul>
</fmt:bundle>