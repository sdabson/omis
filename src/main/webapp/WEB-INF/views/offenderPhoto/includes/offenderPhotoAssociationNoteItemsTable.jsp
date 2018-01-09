<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offenderphoto.msgs.offenderPhoto">
<table id="offenderPhotoAssociationNotesTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="offenderPhotoAssociationNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/offenderPhoto/offenderPhotoAssociationNoteItemsActionMenu.html?offenderPhotoAssociationNoteItemIndex=${offenderPhotoAssociationNoteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>			
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="offenderPhotoAssociationNotesTableBody">
		<jsp:include page="offenderPhotoAssociationNoteItemTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>