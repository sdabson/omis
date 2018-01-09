<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<table id="accommodationNotesTable" class="editTable">
<thead>
<tr>
	<th class="operations"><a class="actionMenuItem" id="accommodationNoteActionMenuLink" href="${pageContext.request.contextPath}/adaAccommodation/accommodationNotesActionMenu.html"></a></th>
	<th class="date"><fmt:message key="noteDateLabel"/></th>
	<th><fmt:message key="notesLabel"/></th>
</tr>
</thead>
	<tbody id="accommodationNotes">
	<c:forEach var="accommodationNote" items="${accommodationForm.accommodationNotes}" varStatus="status">
		<c:if test="${not empty accommodationNote.operation}">	
		<c:set var="accommodationNote" value="${accommodationNote}" scope="request"/>
		<c:set var="noteIndex" value="${status.index}" scope="request"/>			
		<jsp:include page="accommodationNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>