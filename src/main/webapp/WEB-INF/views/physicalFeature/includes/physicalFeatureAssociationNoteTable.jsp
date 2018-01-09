<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
<table id="physicalFeatureAssociaitonNotesTable" class="editTable">
	<thead class="notes">
		<tr>
			<th class="operations"><a class="actionMenuItem" id="physicalFeatureAssociationNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/physicalFeature/physicalFeatureAssociaitonNoteItemsActionMenu.html"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>			
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="physicalFeatureAssociationNotesTableBody">
		<jsp:include page="physicalFeatureAssociaitonNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>