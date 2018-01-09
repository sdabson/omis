<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<tr id="disciplinaryCodeViolationItemRow${disciplinaryCodeViolationItemIndex}" class="disciplinaryCodeViolationItemRow">
		<td>
			<a class="removeLink" id="removeDisciplinaryCodeViolationLink${disciplinaryCodeViolationItemIndex}" href="${pageContext.request.contextPath}/violationEvent/removeDisciplinaryCodeViolation.html?violationEvent=${violationEvent.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="disciplinaryCodeViolationId${disciplinaryCodeViolationItemIndex}" name="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].disciplinaryCodeViolation" value="${disciplinaryCodeViolationItem.disciplinaryCodeViolation.id}"/>
			<form:errors path="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].disciplinaryCodeViolation" cssClass="error"/>
			<input type="hidden" id="disciplinaryCodeViolationOperation${disciplinaryCodeViolationItemIndex}" name="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].itemOperation" value="${disciplinaryCodeViolationItem.itemOperation}"/>
			<form:errors path="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<select id="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].disciplinaryCode" name="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].disciplinaryCode">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${disciplinaryCodes}" var="code">
					<option value="${code.id}" ${code eq disciplinaryCodeViolationItem.disciplinaryCode ? 'selected="selected"' : ''} >
						<c:out value="${code.value} - ${code.description}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="disciplinaryCodeViolationItems[${disciplinaryCodeViolationItemIndex}].disciplinaryCode" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>