<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<table id="disciplinaryCodeViolationTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="disciplinaryCodeViolationItemsActionMenuLink" href="${pageContext.request.contextPath}/violationEvent/disciplinaryCodeViolationItemsActionMenu.html?disciplinaryCodeViolationItemIndex=${disciplinaryCodeViolationItemIndex}"></a></th>
			<th><fmt:message key="violationNameLabel"/></th>
		</tr>
	</thead>
	<tbody id="disciplinaryCodeViolationTableBody">
		<jsp:include page="disciplinaryCodeViolationTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>