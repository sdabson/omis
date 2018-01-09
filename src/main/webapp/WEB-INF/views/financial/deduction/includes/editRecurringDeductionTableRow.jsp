<%--
 - Table row to edit recurring deduction.
 -
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<tr id="recurringDeductionItems[${recurringDeductionItemIndex}].row">
	
<td>
		<sec:authorize access="hasRole('RECURRING_DEDUCTION_REMOVE') or hasRole('ADMIN')">
			<a id="recurringDeductionItems[${recurringDeductionItemIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/financial/removeRecurringDeduction.html"><span class="linkLabel" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		</sec:authorize>
		<input id="recurringDeductionItems[${recurringDeductionItemIndex}].operation" type="hidden" name="recurringDeductionItems[${recurringDeductionItemIndex}].operation" value="${recurringDeductionItem.operation.name}"/>
		<input type="hidden" name="recurringDeductionItems[${recurringDeductionItemIndex}].recurringDeduction" value="${recurringDeductionItem.recurringDeduction.id}"/>
	</td>
<td>
		<select name="recurringDeductionItems[${recurringDeductionItemIndex}].category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="recurringDeductionCategory" items="${recurringDeductionCategories}">
				<c:choose>
					<c:when test="${recurringDeductionItem.category eq recurringDeductionCategory}">
						<option value="${recurringDeductionCategory.id}" selected="selected"><c:out value="${recurringDeductionCategory.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${recurringDeductionCategory.id}"><c:out value="${recurringDeductionCategory.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="recurringDeductionItems[${recurringDeductionItemIndex}].category" cssClass="error"/>
	</td>
	<td>
		<textarea name="recurringDeductionItems[${recurringDeductionItemIndex}].description"><c:out value="${recurringDeductionItem.description}"/></textarea>
		<form:errors path="recurringDeductionItems[${recurringDeductionItemIndex}].description" cssClass="error"/>
	</td>
	<td>
		<input id="recurringDeductionItems[${recurringDeductionItemIndex}].reportedDate" name="recurringDeductionItems[${recurringDeductionItemIndex}].reportedDate" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${recurringDeductionItem.reportedDate}'/>"/>
		<form:errors path="recurringDeductionItems[${recurringDeductionItemIndex}].reportedDate" cssClass="error"/>
	</td>
	<td>
		<input name="recurringDeductionItems[${recurringDeductionItemIndex}].amount" type="text" value="<fmt:formatNumber minFractionDigits="2" value='${fn:replace(fn:replace(recurringDeductionItem.amount, "$", ""), ",", "")}'/>"/>
		<form:errors path="recurringDeductionItems[${recurringDeductionItemIndex}].amount" cssClass="error"/>
	</td>

<td>
		<select name="recurringDeductionItems[${recurringDeductionItemIndex}].frequency">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="recurringDeductionFrequency" items="${recurringDeductionFrequencies}">
				<c:choose>
					<c:when test="${recurringDeductionItem.frequency eq recurringDeductionFrequency}">
						<option value="${recurringDeductionFrequency.name}" selected="selected"><fmt:message key="recurringDeductionFrequencyLabel.${recurringDeductionFrequency.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${recurringDeductionFrequency.name}"><fmt:message key="recurringDeductionFrequencyLabel.${recurringDeductionFrequency.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="recurringDeductionItems[${recurringDeductionItemIndex}].frequency" cssClass="error"/>
	</td>	
	
</tr>