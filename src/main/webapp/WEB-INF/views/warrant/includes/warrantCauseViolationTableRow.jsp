<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.warrant.msgs.warrant">
	<tr id="warrantCauseViolationItemRow${warrantCauseViolationItemIndex}" class="warrantCauseViolationItemRow">
		<td>
			<a class="removeLink" id="removeWarrantCauseViolationLink${warrantCauseViolationItemIndex}" href="${pageContext.request.contextPath}/warrant/removeWarrantCauseViolation.html?warrant=${warrant.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="warrantCauseViolationId${warrantCauseViolationItemIndex}" name="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].warrantCauseViolation" value="${warrantCauseViolationItem.warrantCauseViolation.id}"/>
			<form:errors path="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].warrantCauseViolation" cssClass="error"/>
			<input type="hidden" id="warrantCourtCaseId${warrantCauseViolationItemIndex}" name="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].courtCase" value="${warrantCauseViolationItem.courtCase.id}"/>
			<form:errors path="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].courtCase" cssClass="error"/>
			<input type="hidden" id="warrantCauseViolationOperation${warrantCauseViolationItemIndex}" name="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].itemOperation" value="${warrantCauseViolationItem.itemOperation}"/>
			<form:errors path="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<!-- Court Case Label -->
			<c:out value="${warrantCauseViolationItem.courtCase.docket.value}" />
		</td>
		<td>
			<!-- Conditions Select -->
			<select id="warrantCondition${warrantCauseViolationItemIndex}" name="warrantCondition${warrantCauseViolationItemIndex}">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${conditions}" var="cond">
					<option value="${cond.id}" ${cond eq warrantCauseViolationItem.condition ? 'selected="selected"' : ''} >
						<c:out value="${cond.clause}"/>
					</option>
				</c:forEach>
			</select>
			<input type="hidden" id="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].condition" name="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].condition"  value="${warrantCauseViolationItem.condition.id}" />
			<form:errors path="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].condition" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].description" id="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].description" style="width: 95%"><c:out value="${warrantCauseViolationItem.description}"/></textarea>
			<form:errors path="warrantCauseViolationItems[${warrantCauseViolationItemIndex}].description" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 