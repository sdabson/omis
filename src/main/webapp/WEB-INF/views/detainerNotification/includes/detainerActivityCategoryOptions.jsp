<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<jsp:include page="../../includes/nullOption.jsp"/>
<c:forEach items="${detainerActivityCategories}" var="category">
	<option value="${category.id}">
		<c:out value="${category.name}${not empty category.description ? ' - ' : ''}${category.description}"/>
	</option>
</c:forEach>