<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.assessment.msgs.assessmentRating">
	<ul>
		<sec:authorize access="hasRole('ASSESSMENT_CREATE') or hasRole('ASSESSMENT_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createAssessmentCategoryOverrideNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/assessment/rating/createAssessmentCategoryOverrideNoteItem.html?assessmentCategoryOverrideNoteItemIndex=${assessmentCategoryOverrideNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addAssessmentCategoryOverrideNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>