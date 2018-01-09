<%-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 4, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
	<ul>
		<li>
			<a id="createBoardItineraryNoteLink" class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/addBoardItineraryNote.html?boardItineraryNoteIndex=${boardItineraryNoteIndex}"><span class="visibleLinkLabel"><fmt:message key="addBoardItineraryNoteLink"/></span></a>
		</li>
	</ul>
</fmt:bundle>