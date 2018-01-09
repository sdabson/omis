<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/editSection.html?offender=${offender.id}&administeredQuestionnaireSectionStatus=${administeredQuestionnaireSectionStatus.id}"><span class="visibleLinkLabel"><fmt:message key="administerLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>