<%--
 - Author: Stephen Abson
 - Since: OMIS 3.0 
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<c:forEach var="healthRequestSummary" items="${healthRequestSummaries}">
	<tr>
		<td>
			<sec:authorize access="hasRole('APP_DEV') and (hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('HEALTH_REQUEST_REMOVE'))">
				<a href="${pageContext.request.contextPath}/health/request/remove.html?healthRequest=${healthRequestSummary.id}&amp;referralType=${referralType.name}" class="removeLink" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</sec:authorize>
		</td>
		<td>
			<c:out value="${healthRequestSummary.offenderLastName}"/>, <c:out value="${healthRequestSummary.offenderFirstName}"/> (<c:out value="${healthRequestSummary.offenderNumber}"/>)
		</td>
		<td>
			<c:if test="${healthRequestSummary.asap}"><fmt:message key="yesLabel" bundle="${commonBundle}"/></c:if>
		</td>
		<td>
			<fmt:formatDate value="${healthRequestSummary.date}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:message key="healthRequestCategoryLabel.${healthRequestSummary.category.name}"/>
		</td>
		<td>
			<fmt:message key="healthRequestStatusLabel.${healthRequestSummary.status.name}"/>
		</td>
		<td>
			<c:out value="${healthRequestSummary.notes}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>