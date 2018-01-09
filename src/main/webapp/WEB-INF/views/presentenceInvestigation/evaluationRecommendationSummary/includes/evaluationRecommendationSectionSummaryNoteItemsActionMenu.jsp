<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.evaluationRecommendationSectionSummary">
	<ul>
		<sec:authorize access="hasRole('EVALUATION_RECOMMENDATION_SECTION_SUMMARY_CREATE') or hasRole('EVALUATION_RECOMMENDATION_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createEvaluationRecommendationSectionSummaryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/evaluationRecommendationSummary/createEvaluationRecommendationSectionSummaryNoteItem.html?evaluationRecommendationSectionSummaryNoteItemIndex=${evaluationRecommendationSectionSummaryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addEvaluationRecommendationSectionSummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>