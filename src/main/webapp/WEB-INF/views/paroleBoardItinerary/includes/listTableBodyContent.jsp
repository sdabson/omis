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
<c:forEach var="paroleBoardItinerary" items="${paroleBoardItinerarySummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/paroleBoardItinerary/paroleBoardItinerariesActionMenu.html?paroleBoardItinerary=${paroleBoardItinerary.id}"></a>
		</td>
		<td><fmt:formatDate value="${paroleBoardItinerary.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${paroleBoardItinerary.endDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${paroleBoardItinerary.locationOrganizationName}"/></td>
		<td>
			<table>
				<c:forEach var="boardAttendee" items="${boardAttendeeSummaries[paroleBoardItinerary.id]}" varStatus="status">
					<tr>
						<td>
							<c:out value="${boardAttendee.memberLastName}"/>,
							<c:out value="${boardAttendee.memberFirstName}"/>
							- <c:out value="${boardAttendee.staffTitle}"/>
							<c:if test="${not empty boardAttendee.memberMiddleName}"><c:out value="${boardAttendee.memberMiddleName}"/></c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td><c:out value="${paroleBoardItinerary.count}"/></td>
	</tr>
</c:forEach>
</fmt:bundle>