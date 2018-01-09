<%--
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<c:forEach var="staffTitle" items="${staffTitles}">
	<tr>
		<td>
				<sec:authorize access="hasRole('STAFF_TITLE_EDIT') or hasRole('STAFF_TITLE_VIEW') or hasRole('ADMIN')">
					<a class="viewEditLink" href="${pageContext.request.contextPath}/staff/title/edit.html?staffTitle=${staffTitle.id}" title="<fmt:message key='viewEditLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
				</sec:authorize>
				<sec:authorize access="hasRole('STAFF_TITLE_REMOVE') or hasRole('ADMIN')">
					<a class="removeLink" href="${pageContext.request.contextPath}/staff/title/remove.html?staffTitle=${staffTitle.id}" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
				</sec:authorize>
		</td>
		<td><c:out value="${staffTitle.name}"/></td>
		<td><c:out value="${staffTitle.sortOrder}"/></td>
		<td>
			<c:choose>
				<c:when test="${staffTitle.valid}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>