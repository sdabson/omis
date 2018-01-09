<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.search.msgs.search">

<table id="personSearchResults" class="listTable">
	<thead>
		<tr>
			<th></th>
			<th><fmt:message key="nameLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>