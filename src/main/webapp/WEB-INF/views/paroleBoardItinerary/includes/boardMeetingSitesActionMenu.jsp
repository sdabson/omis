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
			<a id="createBoardMeetingSiteSecureFacilityLink" class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/addBoardMeetingSite.html"><span class="visibleLinkLabel"><fmt:message key="addBoardMeetingSiteSecureFacilityLink"/></span></a>
		</li>
		<li>
			<a id="createBoardMeetingSiteJailLink" class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/addBoardMeetingSite.html"><span class="visibleLinkLabel"><fmt:message key="addBoardMeetingSiteJailLink"/></span></a>
		</li>
		<li>
			<a id="createBoardMeetingSitePrereleaseLink" class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/addBoardMeetingSite.html"><span class="visibleLinkLabel"><fmt:message key="addBoardMeetingSitePrereleaseLink"/></span></a>
		</li>
		<li>
			<a id="createBoardMeetingSiteCommunitySupervisionOfficeLink" class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/addBoardMeetingSite.html"><span class="visibleLinkLabel"><fmt:message key="addBoardMeetingSiteCommunitySupervisionOfficeLink"/></span></a>
		</li>
		<li>
			<a id="createBoardMeetingSiteTreatementAndSanctionCenterLink" class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/addBoardMeetingSite.html"><span class="visibleLinkLabel"><fmt:message key="addBoardMeetingSiteTreatementAndSanctionCenterLink"/></span></a>
		</li>
	</ul>
</fmt:bundle>