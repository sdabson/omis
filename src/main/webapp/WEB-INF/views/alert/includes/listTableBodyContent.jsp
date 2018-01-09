<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Dec 17, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.alert.msgs.alert">
<c:forEach var="alert" items="${alerts}">
	<tr>
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/alert/alertsActionMenu.html?alert=${alert.id}"></a>
		</td>
		<td><c:out value="${alert.description}"/></td>
		<td><fmt:formatDate value="${alert.expireDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${alert.resolution.description}"/></td>
		<td><fmt:formatDate value="${alert.resolution.date}" pattern="MM/dd/yyyy"/></td>
		<td>
			<c:if test="${not empty alert.resolution.resolvedBy}">
				<c:out value="${alert.resolution.resolvedBy.name.lastName}"/>, <c:out value="${alert.resolution.resolvedBy.name.firstName}"/>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>