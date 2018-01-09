<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/sectionList.html?offender=${offender.id}&administeredQuestionnaire=${administeredQuestionnaire.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}" /></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/review.html?offender=${offender.id}&administeredQuestionnaire=${administeredQuestionnaire.id}"><span class="visibleLinkLabel"><fmt:message key="completeLink" /></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('QUESTIONNAIRE_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/questionnaire/removeAdministeredQuestionnaire.html?offender=${offender.id}&administeredQuestionnaire=${administeredQuestionnaire.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>