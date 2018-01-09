<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<tr id="separationNeedReasonItemRow${separationNeedReasonItemIndex}" class="separationNeedReasonItemRow">
		<td>
			<a class="removeLink" id="removeLink${separationNeedReasonItemIndex}" href="${pageContext.request.contextPath}/separationNeed/removeSeparationNeedAssociation.html?separationNeedAssociation=${separationNeedReasonItem.association.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="separationNeedReasonAssociationId${separationNeedReasonItemIndex}" name="separationNeedReasonItems[${separationNeedReasonItemIndex}].association" value="${separationNeedReasonItem.association.id}"/>
			<form:errors path="separationNeedReasonItems[${separationNeedReasonItemIndex}].association" cssClass="error"/>
			<input type="hidden" id="separationNeedReasonItems[${separationNeedReasonItemIndex}].operation" name="separationNeedReasonItems[${separationNeedReasonItemIndex}].operation" value="${separationNeedReasonItem.operation}"/>
			<form:errors path="separationNeedReasonItems[${separationNeedReasonItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<select name="separationNeedReasonItems[${separationNeedReasonItemIndex}].reason" id="separationNeedReasonItems[${separationNeedReasonItemIndex}].reason">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach var="reason" items="${reasons}" varStatus="status">
					<c:choose>
						<c:when test="${separationNeedReasonItem.reason eq reason}">
							<option value="${reason.id}" selected="selected"><c:out value="${reason.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${reason.id}"><c:out value="${reason.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="separationNeedReasonItems[${separationNeedReasonItemIndex}].reason" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty separationNeedReasonItem.active}">
				<c:if test="${separationNeedReasonItem.active}">
							<c:set var="trueSelected" value='selected="selected"'/>
				</c:if>
				<c:if test="${not separationNeedReasonItem.active}">
							<c:set var="falseSelected" value='selected="selected"'/>
				</c:if>
			</c:if>
			<select name="separationNeedReasonItems[${separationNeedReasonItemIndex}].active" id="separationNeedReasonItems[${separationNeedReasonItemIndex}].active">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<option value="true" ${trueSelected}><fmt:message key="yesLabel"/></option>
				<option value="false" ${falseSelected}><fmt:message key="noLabel"/></option>
			</select>
			<form:errors path="separationNeedReasonItems[${separationNeedReasonItemIndex}].active" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>