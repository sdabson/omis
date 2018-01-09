<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.search.msgs.search">

<table id="userSearchResults" class="listTable">
	<thead>
		<tr>
			<th><fmt:message key="load"/></th>
			<th><fmt:message key="nameLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>