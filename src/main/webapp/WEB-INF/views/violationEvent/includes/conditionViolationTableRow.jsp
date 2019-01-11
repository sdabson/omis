<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<tr id="conditionViolationItemRow${conditionViolationItemIndex}" class="conditionViolationItemRow">
		<td>
			<a class="removeLink" id="removeConditionViolationLink${conditionViolationItemIndex}" href="${pageContext.request.contextPath}/violationEvent/removeConditionViolation.html?violationEvent=${violationEvent.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="conditionViolationId${conditionViolationItemIndex}" name="conditionViolationItems[${conditionViolationItemIndex}].conditionViolation" value="${conditionViolationItem.conditionViolation.id}"/>
			<form:errors path="conditionViolationItems[${conditionViolationItemIndex}].conditionViolation" cssClass="error"/>
			<input type="hidden" id="conditionViolationOperation${conditionViolationItemIndex}" name="conditionViolationItems[${conditionViolationItemIndex}].itemOperation" value="${conditionViolationItem.itemOperation}"/>
			<form:errors path="conditionViolationItems[${conditionViolationItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<select id="conditionViolationItems[${conditionViolationItemIndex}].condition" name="conditionViolationItems[${conditionViolationItemIndex}].condition" class="conditionSelect">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${conditions}" var="cond">
					<option value="${cond.id}" ${cond eq conditionViolationItem.condition ? 'selected="selected"' : ''} >
						<c:out value="${cond.conditionClause.conditionTitle.title}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="conditionViolationItems[${conditionViolationItemIndex}].condition" cssClass="error"/>
		</td>
		<td>
			<span id="conditionViolationItemDescription${conditionViolationItemIndex}">
				<c:out value="${conditionViolationItem.condition.conditionClause.description}"/>
			</span>
		</td>
		<td>
			<textarea style="width: 95%; max-width: 95%;" type="text" rows="5" value="${conditionViolationItem.details}" name="conditionViolationItems[${conditionViolationItemIndex}].details" id="conditionViolationItems[${conditionViolationItemIndex}].details" class="details"><c:out value="${conditionViolationItem.details}"/></textarea>
			<form:errors path="conditionViolationItems[${conditionViolationItemIndex}].details" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>