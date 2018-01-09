<%--
 - Author: Yidong Li
 - Version: 0.1.0 (June 6, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.commitstatus.msgs.commitStatus">
	<c:forEach var="commitStatusTermItem" items="${commitStatusTermSummaries}">
		<tr>
			<td><a class="actionMenuItem rowActionMenuItem" id="rowActionMenu" href="${pageContext.request.contextPath}/commitStatus/commitStatusRowActionMenu.html?commitStatusTerm=${commitStatusTermItem.id}"></a></td>
			<td><fmt:formatDate value="${commitStatusTermItem.startDate}" pattern="MM/dd/yyyy"/></td>
			<td><fmt:formatDate value="${commitStatusTermItem.endDate}" pattern="MM/dd/yyyy"/></td>
			<td><c:out value="${commitStatusTermItem.statusName}"/></td>
		</tr>
	</c:forEach>
</fmt:bundle>