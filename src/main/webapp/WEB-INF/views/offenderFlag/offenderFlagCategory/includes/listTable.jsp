<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">

<table id="offenderFlagCategories" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="nameLabel"/></th>			
			<th><fmt:message key="descriptionCodeLabel"/></th>
			<th><fmt:message key="requiredCodeLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>