<%--
  - Family association note edit/creation table.
  -
  - Author: Yidong Li
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.family.msgs.family" var="familyBundle"/>
<fmt:bundle basename="omis.family.msgs.family">
	<table id="noteTable" class="editTable">
		<thead>
			<tr>
			<th><a class="actionMenuItem" id="noteActionMenuLink" href="${pageContext.request.contextPath}/family/familyAssociationNotesActionMenu.html?offender=${offenderSummary.id}"></a><span class="visibleLinkLabel"/></span></th>
			<th><fmt:message key="noteDateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			</tr>
		</thead>
		<tbody id="emailBody">
			<c:forEach var="noteItem" items="${familyAssociationNoteItems}" varStatus="status">
				<c:set var="familyAssociationNoteItem" value="${noteItem}" scope="request"/>
				<c:set var="familyAssociationNoteIndex" value="${status.index}" scope="request"/>
				<jsp:include page="noteItem.jsp"/>
			</c:forEach>
		</tbody>
	</table>
</fmt:bundle>