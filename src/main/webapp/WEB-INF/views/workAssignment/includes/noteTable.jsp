<%--
  - Family association note edit/creation table.
  -
  - Author: Yidong Li
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.family.msgs.family" var="familyBundle"/>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
	<table id="noteTable" class="editTable">
		<thead>
			<tr>
			<th><a class="actionMenuItem" id="workAssignmentNoteActionMenuLink" href="${pageContext.request.contextPath}/workAssignment/workAssignmentNoteActionMenu.html?offender=${offenderSummary.id}"></a><span class="visibleLinkLabel"/></span></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			</tr>
		</thead>
		<tbody id="noteBody">
			<c:forEach var="noteItem" items="${workAssignmentNoteItems}" varStatus="status">
				<c:if test="${noteItem.operation != null}">
					<c:set var="workAssignmentNoteItem" value="${noteItem}" scope="request"/>
					<c:set var="workAssignmentNoteIndex" value="${status.index}" scope="request"/>
					<jsp:include page="workAssignmentNoteItem.jsp"/>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
</fmt:bundle>