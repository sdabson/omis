<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<br>
<table id="feeRequirementsTable" class="editTable">
<thead>	
<tr>
	<th class="operations"/>
	<th class="date"><fmt:message key="startDateLabel"/></th>
	<th class="date"><fmt:message key="endDateLabel"/></th>
	<th><fmt:message key="feeRequirementLabel"/></th>
	<th><fmt:message key="reasonLabel"/></th>
	<th><fmt:message key="authorityCategoryLabel"/></th>
	<th><fmt:message key="commentLabel"/></th>
</tr>
</thead>
	<tbody id="feeRequirements">
	<c:forEach var="feeRequirement" items="${supervisionFeeForm.feeRequirements}" varStatus="status">
		<c:if test="${not empty feeRequirement.operation.name}">
		<c:set var="feeRequirement" value="${feeRequirement}" scope="request"/>
		<c:set var="feeRequirementIndex" value="${status.index}" scope="request"/>
		<jsp:include page="feeRequirementTableRow.jsp"/>
		</c:if>
	</c:forEach>		
	</tbody>
</table>
</fmt:bundle>