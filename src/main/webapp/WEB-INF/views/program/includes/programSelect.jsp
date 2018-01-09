<%--
  - Program select.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<select name="program" id="program">
	<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
	<c:forEach var="program" items="${programs}">
		<c:choose>
			<c:when test="${program eq defaultProgram}"><option value="${program.id}" selected="selected"><c:out value="${program.name}"/></option></c:when>
			<c:otherwise><option value="${program.id}"><c:out value="${program.name}"/></option></c:otherwise>
		</c:choose>
	</c:forEach>
</select>