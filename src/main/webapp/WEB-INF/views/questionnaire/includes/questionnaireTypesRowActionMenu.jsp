<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<ul>
		<c:if test="${administeredQuestionnaireCount eq 0}">
			<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireTypeEdit.html?questionnaireType=${questionnaireType.id}">
					<span class="visibleLinkLabel"><fmt:message key="questionnaireTypeEditLink"/></span></a>
				</li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('QUESTIONNAIRE_REMOVE') or hasRole('ADMIN')">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireTypeRemove.html?questionnaireType=${questionnaireType.id}">
					<span class="visibleLinkLabel"><fmt:message key="removeTypeLink" /></span></a>
				</li>
			</sec:authorize>
		</c:if>
		<sec:authorize access="hasRole('QUESTIONNAIRE_EDIT') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireTypeCopy.html?questionnaireType=${questionnaireType.id}">
					<span class="visibleLinkLabel"><fmt:message key="questionnaireTypeCopyLink"/></span></a>
				</li>
			</sec:authorize>
		<sec:authorize access="hasRole('QUESTIONNAIRE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionList.html?questionnaireType=${questionnaireType.id}">
				<span class="visibleLinkLabel"><fmt:message key="listSectionsLink" /></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>