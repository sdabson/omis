<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<jsp:include page="../../includes/nullOption.jsp"/>
<c:forEach items="${locations}" var="loc">
	<option value="${loc.id}" ${loc == hearingForm.location ? 'selected="selected"' : ''} >
		<c:out value="${loc.organization.name}"/>
	</option>
</c:forEach>