<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Dec 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.trackeddocument.msgs.trackedDocument">
	<c:forEach var="trackedDocumentSummaryItem" items="${docketDocumentReceivalSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem trackedDocumentListRowActionMenuItem" id="trackedDocumentActionMenuLink${status.index}" href="${pageContext.request.contextPath}/trackedDocumentReport/trackedDocumentListRowActionMenu.html?docket=${trackedDocumentSummaryItem.docketId}"></a>	
		</td>
		<td>
			<c:out value="${trackedDocumentSummaryItem.docketValue}"/> - <c:out value="${trackedDocumentSummaryItem.courtName}"/>
		</td>
		<td>
			<c:out value="${trackedDocumentSummaryItem.count}"/>
		</td>
	</tr>
	</c:forEach>
</fmt:bundle>