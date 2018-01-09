<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<jsp:include page="../../includes/nullOption.jsp"/>
<c:forEach items="${conditions}" var="cond">
	<option value="${cond.id}">
		<c:out value="${cond.conditionClause.conditionTitle.title}"/>
	</option>
</c:forEach>