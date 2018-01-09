<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<c:forEach var="summary" items="${questionnaireSectionSummaries}">
<tr>
	<td><c:if test="${administeredQuestionnaire.draft}">
			<a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/questionnaire/administeredQuestionnaireSectionsRowActionMenu.html?offender=${offender.id}&administeredQuestionnaireSectionStatus=${summary.questionnaireSectionStatusId}"></a>
		</c:if>
	</td>
	<td>
		<c:out value="${summary.number}. ${summary.title}"/>
	</td>
	<td>
		<c:out value="${summary.sectionTypeDescription}"/>
	</td>
	<td>
		<c:if test="${summary.draft}">
			<fmt:message key="draftLabel"/>
		</c:if>
		<c:if test="${not summary.draft}">
			<fmt:message key="completedLabel"/>
		</c:if>
	</td>
</tr>
</c:forEach>
</fmt:bundle>