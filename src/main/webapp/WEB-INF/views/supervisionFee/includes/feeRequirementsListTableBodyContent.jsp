<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee"> 
<c:set var="supervisionFeeRequirements" value="${supervisionFeeSummary.supervisionFeeRequirements}"></c:set>
<c:forEach var="feeRequirement" items="${supervisionFeeRequirements}">
	<c:choose>
		<c:when test="${feeRequirement.active}">
			<c:set var="class" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="class" value=""/>
		</c:otherwise>
	</c:choose>
	<tr class="${class}">
		<td></td>		
		<td><fmt:formatDate value="${feeRequirement.startDate}" pattern="MM/dd/yyyy"/></td>		
		<td><fmt:formatDate value="${feeRequirement.endDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatNumber value="${feeRequirement.fee}" type="currency"/></td>
		<td><c:out value="${feeRequirement.reason}"/></td>
		<td><c:out value="${feeRequirement.authority}"/></td>
	</tr>	
</c:forEach>
</fmt:bundle>