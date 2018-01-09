<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<c:forEach var="monthlySupervisionFee" items="${supervisionFeeSummaries}">
<c:set var="supervisionFeeSummary" value="${monthlySupervisionFee}" scope="request"/>
<c:choose>
	<c:when test="${monthlySupervisionFee.active}">
		<c:set var="class" value="rowsOddEven activeRecord"/>
	</c:when>
	<c:otherwise>
		<c:set var="class" value="rowsOddEven"/>
	</c:otherwise>
</c:choose>
<tr >
	<td>
		<a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/supervisionFee/supervisionFeesRowActionMenu.html?supervisionFeeSummaries=${supervisionFeeSummary.monthlySupervisionFeeId}"></a>
	</td>
	<td><fmt:formatDate value="${monthlySupervisionFee.startDate}" pattern="MM/dd/yyyy"/></td>
	<td><fmt:formatDate value="${monthlySupervisionFee.endDate}" pattern="MM/dd/yyyy"/></td>
	<td><fmt:message key="authorityCategoryLabel.${monthlySupervisionFee.authorityCategory.name}"/></td>
	<td><fmt:formatNumber value="${monthlySupervisionFee.fee}" type="currency"/></td>	
	<td class="comment"><c:out value="${monthlySupervisionFee.comment}"/></td>
</tr>
<c:if test="${not empty monthlySupervisionFee.supervisionFeeRequirements}">
<tr>
	<td></td>
	<td colspan="5">
		<jsp:include page="feeRequirementsListTable.jsp"/>
	</td>
</tr>
</c:if>
</c:forEach>
</fmt:bundle>