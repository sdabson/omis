<%-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 30, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
	<ul>
		<sec:authorize access="hasRole('PAROLE_BOARD_ITINERARY_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/list.html">
				<span class="visibleLinkLabel"><fmt:message key="listParoleBoardItinerariesLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>