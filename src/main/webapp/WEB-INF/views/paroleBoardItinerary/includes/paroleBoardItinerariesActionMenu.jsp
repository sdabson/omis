<%-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 30, 2017)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
	<ul>
		<sec:authorize access="hasRole('PAROLE_BOARD_ITINERARY_CREATE') or hasRole('ADMIN')">
			<c:if test="${empty paroleBoardItinerary}">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/create.html">
						<span class="visibleLinkLabel">
							<fmt:message key="createParoleBoardItineraryLink"/></span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('PAROLE_BOARD_ITINERARY_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty paroleBoardItinerary}">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/edit.html?paroleBoardItinerary=${paroleBoardItinerary.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="viewEditParoleBoardItineraryLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('PAROLE_BOARD_ITINERARY_REMOVE') or hasRole('ADMIN')">
			<c:if test="${not empty paroleBoardItinerary}">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/paroleBoardItinerary/remove.html?paroleBoardItinerary=${paroleBoardItinerary.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="removeParoleBoardItineraryLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>