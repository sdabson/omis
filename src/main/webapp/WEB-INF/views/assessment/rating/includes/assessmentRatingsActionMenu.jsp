<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.assessment.msgs.assessmentRating">
	<ul>
		<sec:authorize access="hasRole('ASSESSMENT_RATING_LIST') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/assessment/rating/list.html?administeredQuestionnaire=${administeredQuestionnaire.id}"><span class="visibleLinkLabel"><fmt:message key="listRatingsLink" /></span></a>
			</li>
		</sec:authorize>
		<c:if test="${reassessable}">
			<sec:authorize access="hasRole('ASSESSMENT_RATING_CREATE') or hasRole('ASSESSMENT_RATING_EDIT') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/assessment/rating/reassess.html?administeredQuestionnaire=${administeredQuestionnaire.id}"><span class="visibleLinkLabel"><fmt:message key="reassessLink" /></span></a>
				</li>
			</sec:authorize>
		</c:if>
	</ul>
</fmt:bundle>