<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.military.msgs.military">
	<c:forEach var="serviceTermSummary" items="${serviceTermSummaries}" varStatus="status">
		<tr>
			<td>
				<a class="actionMenuItem serviceTermActionMenuItem" id="serviceTemrRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/military/militaryServiceTermRowActionMenu.html?serviceTerm=${serviceTermSummary.militaryServiceTermId}"></a>
			</td>
			<td>
				<fmt:formatDate value="${serviceTermSummary.startDate}" pattern="MM/dd/yyyy"/>
			</td>
			<td>
				<fmt:formatDate value="${serviceTermSummary.endDate}" pattern="MM/dd/yyyy"/>
			</td>
			<td>
				<c:out value="${serviceTermSummary.branchName}"/>
			</td>
			<td>
				<c:out value="${serviceTermSummary.dischargeStatusName}"/>
			</td>
		</tr>
	</c:forEach>
</fmt:bundle>