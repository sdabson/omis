<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.user.msgs.userRole">
<table class="listTable">
	<colgroup>
		<col class="operations">
	</colgroup>
	<thead>
		<tr>
		<th/>
		<th><fmt:message key="nameLabel"/></th>
		<th><fmt:message key="descriptionLabel"/></th>
		<th><fmt:message key="groupsLabel"/></th>
		</tr>
	</thead>
	<tbody id="userRoles">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>