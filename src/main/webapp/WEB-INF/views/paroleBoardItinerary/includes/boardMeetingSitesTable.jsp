<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 4, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
<table class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="boardMeetingSitesActionMenuLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/boardMeetingSitesActionMenu.html?boardMeetingSiteIndex=${boardMeetingSiteIndex}"></a></th>
			<th><label><fmt:message key="boardMeetingSiteFacilityLabel"/></label></th>
			<th><label><fmt:message key="boardMeetingSiteOrderLabel"/></label>
			<th><label><fmt:message key="boardMeetingSiteDateLabel"/></label></th>
			<th><label><fmt:message key="lastUpdatedByLabel"/></label></th>
		</tr>
	</thead>
	<tbody id="boardMeetingSites">
	<c:forEach var="boardMeetingSite" items="${paroleBoardItineraryForm.boardMeetingSiteItems}" varStatus="status">
		<c:set var="boardMeetingSiteIndex" value="${status.index}" scope="request"/>
		<c:set var="operation" value="${boardMeetingSite.operation}" scope="request"/>
		<c:if test="${not empty boardMeetingSite.operation}">
			<jsp:include page="boardMeetingSiteTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>