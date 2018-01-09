<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.alert.msgs.alert">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="descriptionLabel"/></th>
			<th class="date"><fmt:message key="expireDateLabel"/></th>
			<th><fmt:message key="resolveDescriptionLabel"/></th>
			<th class="date"><fmt:message key="resolveDateLabel"/></th>
			<th><fmt:message key="resolveByPersonLabel"/></th>
		</tr>
	</thead>
	<tbody id="alerts">
 		<jsp:include page="listTableBodyContent.jsp"/> 
	</tbody>
</table>
</fmt:bundle>