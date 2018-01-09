<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 6, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="member" items="${boardMembers}">
	<c:choose>
		<c:when test="${boardMember eq member}">
			<option value="${member.id}" selected="selected"><c:out value="${member.staffAssignment.staffMember.name.lastName}, ${member.staffAssignment.staffMember.name.firstName} ${member.staffAssignment.staffMember.name.middleName}"/></option>
		</c:when>
		<c:otherwise>
			<option value="${member.id}"><c:out value="${member.staffAssignment.staffMember.name.lastName}, ${member.staffAssignment.staffMember.name.firstName} ${member.staffAssignment.staffMember.name.middleName}"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>