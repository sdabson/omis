<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.education.msgs.education">
<table id="noteTable" class="editTable"> 
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="noteItemsActionMenuLink" href="${pageContext.request.contextPath}/education/noteItemsActionMenu.html?noteItemIndex=${noteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="notesLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="noteTableBody">
		<jsp:include page="noteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>