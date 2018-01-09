<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.warrant.msgs.warrant">
<table id="warrantNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="warrantNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/warrant/warrantNoteItemsActionMenu.html?warrantNoteItemIndex=${warrantNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody id="warrantNoteTableBody">
		<jsp:include page="warrantNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>