<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<c:forEach var="summary" items="${questionnaireSectionSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireSectionsRowActionMenu.html?questionnaireSection=${summary.questionnaireSectionId}"></a></td>
	<td>
		<c:out value="${summary.number}" />
	</td>
	<td>
		<c:out value="${summary.title}" />
	</td>
	<td>
		<c:out value="${summary.sectionTypeDescription}" />
	</td>
</tr>
</c:forEach>
</fmt:bundle>