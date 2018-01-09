<%--
 - Table row to edit financial liability.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<tr id="financialLiabilityItems[${financialLiabilityItemIndex}].row">
	<td>
		<sec:authorize access="hasRole('FINANCIAL_LIABILITY_REMOVE') or hasRole('ADMIN')">
			<a id="financialLiabilityItems[${financialLiabilityItemIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/financial/removeLiability.html"><span class="linkLabel" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		</sec:authorize>
		<input id="financialLiabilityItems[${financialLiabilityItemIndex}].operation" type="hidden" name="financialLiabilityItems[${financialLiabilityItemIndex}].operation" value="${financialLiabilityItem.operation.name}"/>
		<input type="hidden" name="financialLiabilityItems[${financialLiabilityItemIndex}].financialLiability" value="${financialLiabilityItem.financialLiability.id}"/>
	</td>
	<td>
		<select name="financialLiabilityItems[${financialLiabilityItemIndex}].category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="financialLiabilityCategory" items="${financialLiabilityCategories}">
				<c:choose>
					<c:when test="${financialLiabilityItem.category eq financialLiabilityCategory}">
						<option value="${financialLiabilityCategory.id}" selected="selected"><c:out value="${financialLiabilityCategory.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${financialLiabilityCategory.id}"><c:out value="${financialLiabilityCategory.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="financialLiabilityItems[${financialLiabilityItemIndex}].category" cssClass="error"/>
	</td>
	<td>
		<textarea name="financialLiabilityItems[${financialLiabilityItemIndex}].description"><c:out value="${financialLiabilityItem.description}"/></textarea>
		<form:errors path="financialLiabilityItems[${financialLiabilityItemIndex}].description" cssClass="error"/>
	</td>
	<td>
		<input id="financialLiabilityItems[${financialLiabilityItemIndex}].reportedDate" name="financialLiabilityItems[${financialLiabilityItemIndex}].reportedDate" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${financialLiabilityItem.reportedDate}'/>"/>
		<form:errors path="financialLiabilityItems[${financialLiabilityItemIndex}].reportedDate" cssClass="error"/>
	</td>
	<td>
		<input name="financialLiabilityItems[${financialLiabilityItemIndex}].amount" type="text" value="<fmt:formatNumber minFractionDigits="2" value='${fn:replace(fn:replace(financialLiabilityItem.amount, "$", ""), ",", "")}'/>"/> 
		<form:errors path="financialLiabilityItems[${financialLiabilityItemIndex}].amount" cssClass="error"/>
	</td>
</tr>