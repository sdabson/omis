<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Aug 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<c:forEach var="chargeSummary" items="${chargeSummaries}">
	<tr>
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/courtCase/chargesActionMenu.html?charge=${chargeSummary.chargeId}"></a>
		</td>
		<td>
			<c:out value="${chargeSummary.courtName}"/>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/courtCase/edit.html?courtCase=${chargeSummary.courtCaseId}"><c:out value="${chargeSummary.docketValue}"/></a>
		</td>
		<td>
			<c:choose>
				<c:when test="${not empty chargeSummary.offenseUrl}">
					<a href="${chargeSummary.offenseUrl}" target="_blank"><c:out value="${chargeSummary.offenseName}"/></a>
				</c:when>
				<c:otherwise>
					<c:out value="${chargeSummary.offenseName}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:out value="${chargeSummary.counts}"/>
		</td>
		<td>
			<fmt:formatDate value="${chargeSummary.date}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${chargeSummary.fileDate}" pattern="MM/dd/yyyy"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>