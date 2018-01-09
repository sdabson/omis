<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<sec:authorize access="hasRole('QUESTIONNAIRE_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/questionnaire/createAdministeredQuestionnaire.html?offender=${offender.id}&questionnaireType=${questionnaireType.id}"><span class="visibleLinkLabel"><fmt:message key="createQuestionnaireLink"/>&nbsp;<c:out value="${questionnaireType.title}"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>