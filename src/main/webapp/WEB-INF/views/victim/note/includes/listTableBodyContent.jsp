<%-- Author: Stephen Abson --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<c:forEach var="noteSummary" items="${noteSummaries}">
	<tr>
		<td>
			<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_NOTE_EDIT') or hasRole('VICTIM_NOTE_VIEW')">
				<a href="${pageContext.request.contextPath}/victim/note/edit.html?note=${noteSummary.id}" class="viewEditLink" title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_NOTE_REMOVE')">
				<a href="${pageContext.request.contextPath}/victim/note/remove.html?note=${noteSummary.id}" class="removeLink" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</sec:authorize>
		</td>
		<td>
			<c:if test="${noteSummary.associationExists}">
				<c:set var="offenderAssociationDetails">${noteSummary.offenderLastName}, ${noteSummary.offenderFirstName}<c:if test="${not empty noteSummary.offenderMiddleName}"> ${fn:substring(noteSummary.offenderMiddleName, 0, 1)}</c:if> #${noteSummary.offenderNumber}</c:set>
				<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_ASSOCIATION_VIEW')" var="canViewEditAssociation"/>
				<c:choose>
					<c:when test="${canViewEditAssociation}"><a href="${pageContext.request.contextPath}/victim/association/edit.html?victimAssociation=${noteSummary.associationId}" title="<fmt:message key='viewEditVictimAssociationLink' bundle='${victimBundle}'/>"><c:out value="${offenderAssociationDetails}"/></a></c:when>
					<c:otherwise><c:out value="${offenderAssociationDetails}"/></c:otherwise>
				</c:choose>
			</c:if>
		</td>
		<td><fmt:formatDate pattern="MM/dd/yyyy" value="${noteSummary.date}"/></td>
		<td><c:out value="${noteSummary.value}"/></td>
	</tr>
</c:forEach>