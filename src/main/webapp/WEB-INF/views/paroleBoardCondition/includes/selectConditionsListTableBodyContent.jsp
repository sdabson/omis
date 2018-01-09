<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
<c:forEach var="conditionItem" items="${conditionItems}" varStatus="i">
	<tr>
		<td>
			<form:input type="hidden" path="conditionItems[${i.index}].condition" />
			<form:input type="hidden" path="conditionItems[${i.index}].conditionClause" />
			<form:input type="hidden" path="conditionItems[${i.index}].itemOperation" />
			<form:checkbox path="conditionItems[${i.index}].active" class="selectConditionCheckBox"/>
		</td>
		<td>
			<c:out value="${conditionItem.conditionClause.conditionTitle.title}" />
		</td>
		<td>
			<c:set var="hiddenClass" value="" />
			<c:if test="${conditionItem.active and paroleBoardAgreementConditionSelectForm.conditionCategory eq 'SPECIAL'}">
				<c:set var="hiddenClass" value="hidden" />
			</c:if>
			<span id="conditionClause${i.index}" class="${hiddenClass}">
				<c:out value="${conditionItem.conditionClause.description}" />
			</span>
			
			<c:if test="${paroleBoardAgreementConditionSelectForm.conditionCategory eq 'SPECIAL'}">
				<c:set var="textAreaHiddenClass" value="" />
				<c:if test="${not conditionItem.active}">
					<c:set var="textAreaHiddenClass" value="hidden" />
				</c:if>
				<span id="conditionClauseTextarea${i.index}" class="${textAreaHiddenClass}">
					<textarea name="conditionItems[${i.index}].clause" id="conditionItems[${i.index}].clause" rows="4" style="width: 95%"><c:out value="${conditionItem.clause}" /></textarea>
				</span>
				<form:errors path="conditionItems[${i.index}].clause" cssClass="error"/>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>