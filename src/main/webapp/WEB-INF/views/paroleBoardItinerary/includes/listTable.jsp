<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 4, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="startDateLabel"/></th>
			<th><fmt:message key="endDateLabel"/></th>
			<th><fmt:message key="locationLabel"/></th>
			<th><fmt:message key="boardMembersLabel"/></th>
		</tr>
	</thead>
	<tbody id="paroleBoardItineraries">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>