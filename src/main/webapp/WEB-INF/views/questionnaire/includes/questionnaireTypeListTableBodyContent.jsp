<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<c:forEach var="summary" items="${questionnaireTypeSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionnaireTypesRowActionMenu.html?questionnaireType=${summary.questionnaireTypeId}"></a></td>
	<td>
		<c:out value="${summary.categoryDescription}" />
	</td>
	<td>
		<c:out value="${summary.questionnaireTypeTitle}" />
	</td>
	<td>
		<c:out value="${summary.cycle}" />
	</td>
	<td>
		<fmt:formatDate value="${summary.startDate}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<fmt:formatDate value="${summary.endDate}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<c:out value="${summary.valid ? 'Yes' : 'No'}" />
	</td>
</tr>
</c:forEach>
</fmt:bundle>