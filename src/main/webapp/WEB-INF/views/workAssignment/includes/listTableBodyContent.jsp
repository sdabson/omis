<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Aug 25, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
		<c:forEach var="workAssignmentSummaryItem" items="${workAssignmentSummaries}">
		<tr>
			<td>
				<a class="actionMenuItem rowActionMenuItem" id="actionMenuLink${status.index}" href="${pageContext.request.contextPath}/workAssignment/workAssignmentRowActionMenu.html?workAssignment=${workAssignmentSummaryItem.id}&offender=${offender.id}"></a>	
			</td>
			<td><c:out value="${workAssignmentSummaryItem.categoryName}"/> <c:if test="${workAssignmentSummaryItem.categoryGroupName != null }">, <c:out value="${workAssignmentSummaryItem.categoryGroupName}"/> </c:if> </td>
			<td>
			<fmt:formatDate value="${workAssignmentSummaryItem.assignedDate}" pattern="MM/dd/yyyy"/>
			</td>
			<td>
			<fmt:formatDate value="${workAssignmentSummaryItem.terminationDate}" pattern="MM/dd/yyyy"/>
			</td>
			<td><c:out value="${workAssignmentSummaryItem.days}"/></td>
			<td><c:out value="${workAssignmentSummaryItem.fenceRestrictionName}"/></td>
			<td><c:out value="${workAssignmentSummaryItem.comments}"/></td>
		</tr>
	</c:forEach>
</fmt:bundle>