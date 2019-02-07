<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.assessment.msgs.assessmentHome">
<c:if test="${not empty assessmentSummary}">
	<div id="assessmentHeader">
		<fieldset class="foregroundUltraLight">
			<legend class="foregroundLight"><fmt:message key="assessmentHeader" /></legend>
			<span class="oneline">
				<span class="fieldGroup onelineHalf">
					<label class="fieldLabel">
						<fmt:message key="assessmentCategory"/>
					</label>
					<span class="detail">
						<c:out value="${assessmentSummary.questionnaireCategoryName}"/>
					</span>
				</span>
				<span class="fieldGroup onelineHalf">
					<label class="fieldLabel">
						<fmt:message key="assessmentName"/>
					</label>
					<span class="detail">
						<c:out value="${assessmentSummary.questionnaireTypeName}"/>
					</span>
				</span>
			</span>
			<span class="oneline">
				<span class="fieldGroup onelineHalf">
					<label class="fieldLabel">
						<fmt:message key="assessmentDateLabel"/>
					</label>
					<span class="detail">
						<fmt:formatDate value="${assessmentSummary.administeredQuestionnaireDate}" pattern="MM/dd/yyyy" />
					</span>
				</span>
				<span class="fieldGroup onelineHalf">
					<label class="fieldLabel">
						<fmt:message key="assessorNameLabel"/>
					</label>
					<span class="detail">
						<c:out value="${assessmentSummary.administeredQuestionnaireAssessorLastName}, ${assessmentSummary.administeredQuestionnaireAssessorFirstName}"/>
					</span>
				</span>
			</span>
			<span class="oneline">
				<span class="fieldGroup onelineHalf">
					<label class="fieldLabel">
						<fmt:message key="ratingScoreLabel"/>
					</label>
					<c:forEach items="${assessmentRatingSummaries}" var="ratingSummary">
						<c:if test="${ratingSummary.assessmentCategoryScorePertinent}">
							<span class="detail">
								<fmt:message key="ratingSummaryLabel">
									<fmt:param value="${ratingSummary.assessmentRatingDescription}" />
									<fmt:param value="${ratingSummary.assessmentCategoryScoreValue}" />
									<fmt:param value="${ratingSummary.rankName}" />
								</fmt:message>
							</span>
						</c:if>
					</c:forEach>
				</span>
			</span>
		</fieldset>
	</div>
</c:if>
</fmt:bundle>