<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumberCategory">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="nameLabel"/></th>
			<th><fmt:message key="validLabel" /></th></tr>
	</thead>
	<tbody id="identificationNumbers">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>