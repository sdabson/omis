<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editBoardHearing" access="hasRole('BOARD_HEARING_EDIT') or hasRole('ADMIN') or hasRole('BOARD_HEARING_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
<span class="fieldGroup">
	<label for="boardMember1" class="fieldLabel">
		<fmt:message key="boardMember1Label"/>
	</label>
	<select name="boardMember1" id="boardMember1">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<c:forEach items="${boardAttendees}" var="attendee">
			<c:set var="name" value="${attendee.boardMember.staffAssignment.staffMember.name}" />
			<option value="${attendee.boardMember.id}" ${attendee.boardMember == boardMember1 ? 'selected="selected"' : ''}>
				<c:out value="${name.lastName}, ${name.firstName}"/>
			</option>
		</c:forEach>
	</select>
	<form:errors path="boardMember1" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="boardMember2" class="fieldLabel">
		<fmt:message key="boardMember2Label"/>
	</label>
	<select name="boardMember2" id="boardMember2">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<c:forEach items="${boardAttendees}" var="attendee">
			<c:set var="name" value="${attendee.boardMember.staffAssignment.staffMember.name}" />
			<option value="${attendee.boardMember.id}" ${attendee.boardMember == boardMember2 ? 'selected="selected"' : ''}>
				<c:out value="${name.lastName}, ${name.firstName}"/>
			</option>
		</c:forEach>
	</select>
	<form:errors path="boardMember2" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label for="boardMember3" class="fieldLabel">
		<fmt:message key="boardMember3Label"/>
	</label>
	<select name="boardMember3" id="boardMember3">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<c:forEach items="${boardAttendees}" var="attendee">
			<c:set var="name" value="${attendee.boardMember.staffAssignment.staffMember.name}" />
			<option value="${attendee.boardMember.id}" ${attendee.boardMember == boardMember3 ? 'selected="selected"' : ''}>
				<c:out value="${name.lastName}, ${name.firstName}"/>
			</option>
		</c:forEach>
	</select>
	<form:errors path="boardMember3" cssClass="error"/>
</span>
</fmt:bundle>