<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.assessment.msgs.assessmentNotes">
	<ul>
		<sec:authorize access="hasRole('RATING_NOTE_CREATE') or hasRole('RATING_NOTE_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createRatingNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/assessment/rating/notes/createRatingNoteItem.html?ratingNoteItemIndex=${ratingNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addRatingNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>