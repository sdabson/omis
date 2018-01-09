<%--
  - Table of identification numbers.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.identificationnumber.msgs.identificationNumber" var="identificationNumberBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="identificationNumberIssuerLabel" bundle="${identificationNumberBundle}"/></th>
			<th><fmt:message key="identificationNumberCategoryLabel" bundle="${identificationNumberBundle}"/></th>
			<th><fmt:message key="identificationNumberValueLabel" bundle="${identificationNumberBundle}"/></th>
			<th><fmt:message key="identificationNumberIssueDateLabel" bundle="${identificationNumberBundle}"/></th>
			<th><fmt:message key="identificationNumberExpireDateLabel" bundle="${identificationNumberBundle}"/></th>
		</tr>
	</thead>
	<tbody id="identificationNumbers">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>