<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.need.msgs.need">
	<c:forEach var="casePlanObjectiveSummary" items="${casePlanObjectiveSummaries}" varStatus="status">
		<tr>
			<td>
				<a class="actionMenuItem rowActionMenuLink" id="summaryActionMenuLink${status.index}" href="${pageContext.request.contextPath}/need/casePlanObjective/objectiveRowActionMenu.html?objective=${casePlanObjectiveSummary.id}"></a>
			</td>
			<td>
				<c:out value="${casePlanObjectiveSummary.objectiveName}"/>
			</td>
			<td>
				<c:out value="${casePlanObjectiveSummary.source}"/>
			</td>
			<td>
				<fmt:formatDate value="${casePlanObjectiveSummary.date}" pattern="MM/dd/yyyy"/>
			</td>
			<td>
				<c:out value="${casePlanObjectiveSummary.needDomainName}"/>
			</td>
		</tr>
	</c:forEach>
</fmt:bundle>