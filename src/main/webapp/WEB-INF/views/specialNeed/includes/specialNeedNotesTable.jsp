<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
<table id="specialNeedNotesTable" class="editTable">
<thead>
<tr>
	<th class="operations"><a class="actionMenuItem" id="specialNeedNoteActionMenuLink" href="${pageContext.request.contextPath}/specialNeed/specialNeedNotesActionMenu.html"></a></th>
	<th class="date"><fmt:message key="noteDateLabel"/></th>
	<th><fmt:message key="notesLabel"/></th>
</tr>
</thead>
	<tbody id="specialNeedNotes">
	<c:forEach var="specialNeedNote" items="${specialNeedForm.specialNeedNotes}" varStatus="status">
		<c:if test="${not empty specialNeedNote.operation}">	
		<c:set var="specialNeedNote" value="${specialNeedNote}" scope="request"/>
		<c:set var="noteIndex" value="${status.index}" scope="request"/>			
		<jsp:include page="specialNeedNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
	</tbody>
</table>
</fmt:bundle>