<%--
  - List table body content of tasks.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach var="taskSummary" items="${taskSummaries}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/task/tasksActionMenu.html?task=${taskSummary.id}" class="actionMenuItem" id="taskActionMenu${task.id}"></a></td>
		<td><c:out value="${taskSummary.description}"/></td>
		<td><c:out value="${taskSummary.sourceUserLastName}"/>, <c:out value="${taskSummary.sourceUserFirstName}"/> <c:if test="${not empty taskSummary.sourceUserMiddleName}"><c:out value="${fn:substring(taskSummary.sourceUserMiddleName, 0, 1)}"/>. </c:if><c:if test="${not empty taskSummary.sourceUserSuffix}"><c:out value="${taskSummary.sourceUserSuffix}"/> </c:if><c:out value="${taskSummary.sourceUsername}"/></td>
		<td><fmt:formatDate value="${taskSummary.originationDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${taskSummary.completionDate}" pattern="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>