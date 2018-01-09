<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:bundle basename="omis.visitation.msgs.visitation">

<table id="visitationAssociations" class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="visitorNameLabel"/></th>
			<th><fmt:message key="visitorMailingAddressLabel"/></th>
			<th><fmt:message key="visitorPrimaryTelephoneNumberLabel"/>
			<th><fmt:message key="visitorApprovedLabel"/></th>
			<th><fmt:message key="visitorSpecialVisitLabel"/></th>
			<th><fmt:message key="visitorMoneyLabel"/></th>
<!-- 			Commented out because John D. and Mark J. thought unnecessary. Wait until visitation verifies it is necessary. sv -->
			<%-- <th><fmt:message key="visitorSSNLabel"/></th>
			<th class="date"><fmt:message key="visitorDOBLabel"/></th> --%>
			<th><fmt:message key="visitorRelationLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
	<c:forEach var="visitationAssociationSummary" items="${visitationAssociationSummaries}" varStatus="status">
		<c:if test="${visitationAssociationSummary.currentlyVisiting eq false}">
		${currentlyVisiting}
			<div id="dialog${visitationAssociationSummary.id}">
			</div>
		</c:if>
	</c:forEach>
</fmt:bundle>