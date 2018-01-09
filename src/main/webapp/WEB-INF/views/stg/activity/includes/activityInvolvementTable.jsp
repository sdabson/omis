<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.stg.msgs.stg">
<table id="activityInvolvementsTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="activityInvolvementItemsActionMenuLink" href="${pageContext.request.contextPath}/stg/activityInvolvementItemsActionMenu.html?activityInvolvementItemIndex=${activityInvolvementItemIndex}"></a></th>
			<th><fmt:message key="offenderLabel"/></th>
			<th class="narrative"><fmt:message key="narrativeLabel"/></th>
		</tr>
	</thead>
	<tbody id="activityInvolvementsTableBody">
		<jsp:include page="activityInvolvementTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>