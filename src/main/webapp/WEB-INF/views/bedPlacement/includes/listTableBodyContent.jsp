<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
<c:forEach var="bedPlacement" items="${bedPlacements}" varStatus="status">
		<tr class="bedPlacementRow" id="bedPlacement${bedPlacement.id}">
		<td>
			<a class="actionMenuItem bedPlacementRowActionMenuItem" id="bedPlacementRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/bedPlacement/bedPlacementRowActionMenu.html?bedPlacement=${bedPlacement.id}"></a>
		</td>
		<td>
			<c:out value="${bedPlacement.bedPlacementReason.name}"/>
		</td>
		<td>
			<c:out value="${bedPlacement.bed.room.unit.name}"/>
		</td>
		<td>
			<c:out value="${bedPlacement.bed.room.section.name}"/>
		</td>
		<td>
			<c:out value="${bedPlacement.bed.room.level.name}"/>
		</td>
		<td>
			<c:out value="${bedPlacement.bed.room.name}"/>
		</td>
		<td>
			<c:out value="${bedPlacement.bed.number}"/>
		</td>
		<td>
			<fmt:formatDate value="${bedPlacement.dateRange.startDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td class="bedPlacementEndDate" id="bedPlacement${bedPlacement.id}EndDate">
			<fmt:formatDate value="${bedPlacement.dateRange.endDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${bedPlacement.description}"/>
		</td>
		<td class="move" id="moveComplete${bedPlacement.id}">
			<c:if test="${bedPlacement.confirmed}">
				<fmt:message key="bedPlacementConfirmedLabel"/>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>
