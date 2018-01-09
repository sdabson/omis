<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireTypeList.html"><span class="visibleLinkLabel"><fmt:message key="listQuestionnaireTypesLink"/></span></a>
			</li>
		</sec:authorize>
		<c:if test="${administeredQuestionnaireCount eq 0}">
			<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionCreate.html?questionnaireType=${questionnaireType.id}"><span class="visibleLinkLabel"><fmt:message key="createSectionLink" /></span></a>
				</li>
			</sec:authorize>
		</c:if>
	</ul>
</fmt:bundle>