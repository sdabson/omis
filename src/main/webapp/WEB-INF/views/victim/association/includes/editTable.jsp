<%--
 - Edit table for victim notes.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<table class="editTable">
	<thead>
		<tr>
			<th>
				<a class="actionMenuItem" id="notesActionMenuLink" href="${pageContext.request.contextPath}/victim/association/associationNotesActionMenu.html?offender=${offenderSummary.id}&amp;victim=${victimSummary.id}"></a>
			</th>
			<th><fmt:message key="victimNoteCategoryLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimNoteDateLabel" bundle="${victimBundle}"/></th>
			<th><fmt:message key="victimNoteValueLabel" bundle="${victimBundle}"/></th>
		</tr>
	</thead>
	<tbody id="victimAssociationNotesBody">
		<c:forEach var="victimNoteItem" items="${victimNoteItems}" varStatus="status">
			<c:if test="${not empty victimNoteItem.operation}">
				<c:set var="victimNoteItem" value="${victimNoteItem}" scope="request"/>
				<c:set var="victimNoteItemIndex" value="${status.index}" scope="request"/>
				<jsp:include page="/WEB-INF/views/victim/note/includes/editTableRow.jsp"/>
			</c:if>
		</c:forEach>
	</tbody>
</table>