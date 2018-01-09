<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">



<table id="noteTable" class="editTable"> 
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="noteItemsActionMenuLink" href="${pageContext.request.contextPath}/workRestriction/noteItemsActionMenu.html?noteItemIndex=${noteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="notesLabel"/></th>
			<th><fmt:message key="lastUpdateLabel"/></th>
		</tr>
	</thead>
	<tbody id="noteTableBody">
		<jsp:include page="noteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>