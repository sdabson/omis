<%--
 - Table row to edit financial asset.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<tr id="financialAssetItems[${financialAssetItemIndex}].row">
	<td>
		<sec:authorize access="hasRole('FINANCIAL_ASSET_REMOVE') or hasRole('ADMIN')">
			<a id="financialAssetItems[${financialAssetItemIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/financial/removeAsset.html"><span class="linkLabel" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		</sec:authorize>
		<input id="financialAssetItems[${financialAssetItemIndex}].operation" type="hidden" name="financialAssetItems[${financialAssetItemIndex}].operation" value="${financialAssetItem.operation.name}"/>
		<input type="hidden" name="financialAssetItems[${financialAssetItemIndex}].financialAsset" value="${financialAssetItem.financialAsset.id}"/>
	</td>
	<td>
		<select name="financialAssetItems[${financialAssetItemIndex}].category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="financialAssetCategory" items="${financialAssetCategories}">
				<c:choose>
					<c:when test="${financialAssetItem.category eq financialAssetCategory}">
						<option value="${financialAssetCategory.id}" selected="selected"><c:out value="${financialAssetCategory.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${financialAssetCategory.id}"><c:out value="${financialAssetCategory.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="financialAssetItems[${financialAssetItemIndex}].category" cssClass="error"/>
	</td>
	<td>
		<textarea name="financialAssetItems[${financialAssetItemIndex}].description"><c:out value="${financialAssetItem.description}"/></textarea>
		<form:errors path="financialAssetItems[${financialAssetItemIndex}].description" cssClass="error"/>
	</td>
	<td>
		<input id="financialAssetItems[${financialAssetItemIndex}].reportedDate" name="financialAssetItems[${financialAssetItemIndex}].reportedDate" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${financialAssetItem.reportedDate}'/>"/>
		<form:errors path="financialAssetItems[${financialAssetItemIndex}].reportedDate" cssClass="error"/>
	</td>
	<td>
		<input name="financialAssetItems[${financialAssetItemIndex}].amount" type="text" value="<fmt:formatNumber minFractionDigits="2" value='${fn:replace(fn:replace(financialAssetItem.amount, "$", ""), ",", "")}'/>"/>
		<form:errors path="financialAssetItems[${financialAssetItemIndex}].amount" cssClass="error"/>
	</td>
</tr>