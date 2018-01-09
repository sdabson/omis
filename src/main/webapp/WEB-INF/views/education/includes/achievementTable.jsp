<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.education.msgs.education">



<table id="achievementTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="achievementItemsActionMenuLink" href="${pageContext.request.contextPath}/education/achievementItemsActionMenu.html?achievementItemIndex=${achievementItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="categoryLabel"/></th>
			<th><fmt:message key="descriptionLabel"/></th>
		</tr>
	</thead>
	<tbody id="achievementTableBody">
		<jsp:include page="achievementTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>