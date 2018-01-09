<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.task.msgs.task">
	<c:forEach var="taskAssignmentItem" items="${taskAssignmentItems}" varStatus="status">
		<c:set var="taskAssignmentItem" value="${taskAssignmentItem}" scope="request"/>
		<c:set var="taskAssignmentItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty taskAssignmentItem.taskItemOperation}">
			<jsp:include page="taskAssignmentItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>