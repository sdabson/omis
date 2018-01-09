<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.search.msgs.search">

<table id="caseLoadSearchResults" class="listTable">
	<thead>
		<tr>
			<th><fmt:message key="load"/></th>
			<th><fmt:message key="titleLabel"/></th>
			<th><fmt:message key="descriptionLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>