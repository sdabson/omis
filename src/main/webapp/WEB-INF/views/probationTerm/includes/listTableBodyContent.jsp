<%--
 - Table body content of probation terms.
 -
 - Author: Josh Divine
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
<c:forEach var="probationTerm" items="${probationTermSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/probationTerm/probationTermsActionMenu.html?probationTerm=${probationTerm.id}&amp;courtCase=${courtCase.id}"></a>
		</td>
		<c:if test="${empty courtCase}">
				<td><c:out value="${probationTerm.courtCaseDocketValue}"></c:out></td>
		</c:if>
		<td><fmt:formatDate value="${probationTerm.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${probationTerm.terminationDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${probationTerm.sentenceDays}"/></td>
		<td><fmt:formatDate value="${probationTerm.expirationDate}" pattern="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>
</fmt:bundle>