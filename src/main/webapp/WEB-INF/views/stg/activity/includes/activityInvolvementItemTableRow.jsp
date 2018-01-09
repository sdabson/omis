<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<tr id="activityInvolvementItemRow${activityInvolvementItemIndex}" class="activityInvolvementItemRow">
		<td>
			<a class="removeLink" id="removeLink${activityInvolvementItemIndex}" href="${pageContext.request.contextPath}/stg/activity/removeActivityNote.html?activity=${activitySummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="activityInvolvementId${activityInvolvementItemIndex}" name="involvementItems[${activityInvolvementItemIndex}].activityInvolvement" value="${activityInvolvementItem.activityInvolvement.id}"/>
			<form:errors path="involvementItems[${activityInvolvementItemIndex}].activityInvolvement" cssClass="error"/>
			<input type="hidden" id="activityInvolvementOperation${activityInvolvementItemIndex}" name="involvementItems[${activityInvolvementItemIndex}].operation" value="${activityInvolvementItem.operation}"/>
			<form:errors path="involvementItems[${activityInvolvementItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<input type="text" class="fieldLabel" id="activityInvolvementItemOffenderInput${activityInvolvementItemIndex}"/>
			<input type="hidden" name="involvementItems[${activityInvolvementItemIndex}].offender" id="activityInvolvementItemOffender${activityInvolvementItemIndex}" value="${activityInvolvementItem.offender.id}"/>
		<a id="activityInvolvementItemClear${activityInvolvementItemIndex}" class="clearLink"></a>
		<span id="activityInvolvementItemDisplay${activityInvolvementItemIndex}">
			<c:if test="${not empty involvementItems[activityInvolvementItemIndex].offender}">
				<c:out value="${involvementItems[activityInvolvementItemIndex].offender.name.lastName}"/>,
				<c:out value="${involvementItems[activityInvolvementItemIndex].offender.name.firstName}"/>
				<c:out value="(${involvementItems[activityInvolvementItemIndex].offender.offenderNumber})"/>
			</c:if>
		</span>	
			<form:errors path="involvementItems[${activityInvolvementItemIndex}].offender" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="involvementItems[${activityInvolvementItemIndex}].narrative" id="activityInvolvementItems[${activityInvolvementItemIndex}].narrative" style="width: 95%"><c:out value="${activityInvolvementItem.narrative}"/></textarea>
			<form:errors path="involvementItems[${activityInvolvementItemIndex}].narrative" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>