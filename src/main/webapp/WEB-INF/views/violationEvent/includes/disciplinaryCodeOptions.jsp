<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<jsp:include page="../../includes/nullOption.jsp"/>
<c:forEach items="${disciplinaryCodes}" var="code">
	<option value="${code.id}">
		<c:out value="${code.value} - ${code.description}"/>
	</option>
</c:forEach>