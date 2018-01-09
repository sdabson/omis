<%--
  - Victim search results.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.victim.msgs.victim">
	<table id="searchResults">
		<thead>
		<tr>
			<th></th>
			<th><fmt:message key="victimLabel"/>
			<th><fmt:message key="victimAssociationCountLabel"/>
			<th><fmt:message key="victimNoteCountLabel"/>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="victimSummary" items="${victimSummaries}">
			<tr>
				<td>
					<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_PROFILE_VIEW')">
						<a class="victimProfileLink" href="${pageContext.request.contextPath}/victim/profile.html?victim=${victimSummary.id}" class="victimProfileLink" title="<fmt:message key='victimProfileLink'/>"><span class="linkLabel"><fmt:message key="victimProfileLink"/></span></a>
					</sec:authorize>
				</td>
				<td>
					<c:out value="${victimSummary.lastName}"/>,
					<c:out value="${victimSummary.firstName}"/>
					<c:if test="${not empty victimSummary.middleName}">
						<c:out value="${fn:substring(victimSummary.middleName, 0, 1)}"/>
					</c:if>
					<c:if test="${victimSummary.offender}">
						#<c:out value="${victimSummary.offenderNumber}"/>
					</c:if>
				</td>
				<td>
					<c:if test="${not empty victimSummary.associationCount}">
						<sec:authorize var="canListAssociations" access="hasRole('ADMIN') or hasRole('VICTIM_ASSOCIATION_LIST')" />
						<c:choose>
							<c:when test="${canListAssociations}"><a href="${pageContext.request.contextPath}/victim/association/listByVictim.html?victim=${victimSummary.id}" title="<fmt:message key='listVictimAssociationsLink'/>"><c:out value="${victimSummary.associationCount}"/></a></c:when>
							<c:otherwise><c:out value="${victimSummary.associationCount}"/></c:otherwise>
						</c:choose>
					</c:if>
				</td>
				<td>
					<c:if test="${not empty victimSummary.noteCount}">
						<sec:authorize var="canListNotes" access="hasRole('ADMIN') or hasRole('VICTIM_NOTE_LIST')" />
						<c:choose>
							<c:when test="${canListNotes}"><a href="${pageContext.request.contextPath}/victim/note/list.html?victim=${victimSummary.id}" title="<fmt:message key='listVictimNotesLink'/>"><c:out value="${victimSummary.noteCount}"/></a></c:when>
							<c:otherwise><c:out value="${victimSummary.noteCount}"/></c:otherwise>
						</c:choose>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</fmt:bundle>