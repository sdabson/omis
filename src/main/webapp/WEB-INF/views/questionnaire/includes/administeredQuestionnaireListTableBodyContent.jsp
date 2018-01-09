<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<c:forEach var="summary" items="${administeredQuestionnaireSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/questionnaire/administeredQuestionnairesRowActionMenu.html?offender=${offender.id}&administeredQuestionnaire=${summary.questionnaireId}"></a></td>
	<td>
		<c:out value="${summary.questionnaireTitle}"/>
	</td>
	<td>
		<c:out value="${summary.assessorLastName}, ${summary.assessorFirstName}"/>
	</td>
	<td>
		<fmt:formatDate value="${summary.date}" pattern="MM/dd/yyyy"/>
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