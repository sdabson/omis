<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<c:forEach var="summary" items="${questionAnswerSummaries}">
<tr>
	<td>
	<c:if test="${administeredQuestionnaireCount eq 0}">
		<a class="actionMenuItem rowActionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/questionnaire/questionsRowActionMenu.html?allowedQuestion=${summary.key.allowedQuestionId}"></a>
	</c:if>	
	</td>	
	<td>
		<c:out value="${summary.key.questionNumber}" />
	</td>
	<td>
		<c:out value="${summary.key.questionText}" />
	</td>
	<td>
		<c:set value="${summary.key.questionCategory}" var="category" />
		<c:out value="${fn:toUpperCase(fn:substring(category, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(category, '_', ' '), 1, -1))}"/>
	</td>
	<td>
		<c:forEach var="answerSummary" items="${summary.value}" varStatus="status"> 
			<c:set var="commaTest" value="${fn:length(summary.value) gt (status.index+1)}" />
			<c:out value="${answerSummary.description}"/><c:if test="${commaTest}"><c:out value=","/></c:if>
		</c:forEach>
	</td>
	<td>
		<c:set value="${summary.key.answerCardinality}" var="cardinality" />
		<c:out value="${fn:toUpperCase(fn:substring(cardinality, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(cardinality, '_', ' '), 1, -1))}"/>
	</td>
	<td>
		<c:out value="${summary.key.questionHelpText}" />
	</td>
	<td>
		<c:set value="${summary.key.questionConditionality}" var="conditionality" />
		<c:out value="${conditionality eq 'REQUIRED' ? 'Yes' : 'No'}"/>
	</td>
	<td>
		<c:out value="${summary.key.valid ? 'Yes' : 'No'}" />
	</td>
</tr>
</c:forEach>
</fmt:bundle>