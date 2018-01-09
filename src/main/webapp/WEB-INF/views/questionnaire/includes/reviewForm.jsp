<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editQuestionnaire" access="hasRole('QUESTIONNAIRE_CREATE') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<form:form commandName="questionnaireReviewForm" class="editForm">
	<c:forEach var="sectionQuestionSummary" items="${sectionQuestionSummaries}" varStatus="currentSection">
		<c:set var="sectionDraftError"><form:errors path="sectionReviewItems[${currentSection.index}].sectionStatus" /></c:set>
		<c:if test="${not empty sectionDraftError}">
			<br>
			<span class="error">
				<c:out value="${sectionQuestionSummary.key.number}."/>&nbsp;
			</span>
			<form:errors path="sectionReviewItems[${currentSection.index}].sectionStatus" cssClass="error" />
		</c:if>
	</c:forEach>
	
	<c:forEach var="sectionQuestionSummary" items="${sectionQuestionSummaries}" varStatus="currentSection">
		<fieldset>
		<legend>
			<span title="${sectionQuestionSummary.key.sectionHelpText}">
				<label class="fieldLabel">
					<c:out value="${sectionQuestionSummary.key.number}. ${sectionQuestionSummary.key.title}"/>
				</label>
			</span>
		</legend>
		<input type="hidden" name="sectionReviewItems[${currentSection.index}].sectionStatus" value="${questionnaireReviewForm.sectionReviewItems[currentSection.index].sectionStatus.id}"/>
		
		<form:errors path="sectionReviewItems[${currentSection.index}].sectionStatus" cssClass="error" />
		<br>
		<c:set var="sectionId" value="${sectionQuestionSummary.key.questionnaireSectionId}" />
		<c:set var="questionAnswerItems" value="${questionnaireReviewForm.sectionReviewItems[currentSection.index].questionAnswerItems}" />
		<c:set var="sectionNotes" value="${questionnaireReviewForm.sectionReviewItems[currentSection.index].sectionNotes}" />
		
		<table id="questionnaireSectionReviewTable[${currentSection.index}]" class="listTable">
			<thead>
				<tr>
					<th><fmt:message key="questionLabel"/></th>
					<th><fmt:message key="answerLabel"/></th>
					<th><fmt:message key="responseLabel"/></th>
					<th><fmt:message key="questionCommentsLabel"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="questionSummary" items="${sectionQuestionSummary.value}" varStatus="currentQuestion">
				<tr>
					<td>
						<span title="${questionSummary.questionHelpText}">
							<c:out value="${questionSummary.questionNumber}. ${questionSummary.questionText}"/>
						</span>
					</td>
					<td>
						<c:out value="${questionAnswerItems[currentQuestion.index].answerValue.description}"/>
						<c:forEach var="answers" items="${questionAnswerItems[currentQuestion.index].answerValueItems}" varStatus="currentAnswer">
								<c:set var="commaTest" value="${fn:length(questionAnswerItems[currentQuestion.index].answerValueItems) gt (currentAnswer.index+1)}" />
								<c:out value="${answers.answerValue.description}"/><c:if test="${commaTest}"><c:out value=","/></c:if>
						</c:forEach>
					</td>
					<td>
						<c:out value="${questionAnswerItems[currentQuestion.index].answerValueText}" />
					</td>
					<td>
						<c:out value="${questionAnswerItems[currentQuestion.index].comments}" />
					</td>
					</tr>
				</c:forEach>
				<c:if test="${not empty sectionNotes}">
					<tr>
						<td>
							<fmt:message key="sectionCommentsLabel"/>
						</td>
						<td colspan="3">
							<c:out value="${sectionNotes}" />
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		</fieldset>
	</c:forEach>
	
	<c:if test="${editQuestionnaire && administeredQuestionnaireSummary.draft}">
		<p class="buttons">
			<input type="submit" id="saveAsFinal" value="<fmt:message key='finalizeQuestionnaireLabel'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>