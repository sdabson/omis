<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.country.msgs.country" var="countryBundle"/>
<fmt:bundle basename="omis.address.msgs.address">
<table class="listTable" id="addresses">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="deliveryAddressLineHeader"/></th>
			<th><fmt:message key="lastLineHeader"/></th>
			<th><fmt:message key="countryLabel" bundle="${countryBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>