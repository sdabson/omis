<!-- 
 - Author: Josh Divine
 - Version: 0.1.0 (November 22, 2016)
 - Since: OMIS 3.0
 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editFinancial" access="hasRole('FINANCIAL_EDIT') or hasRole('ADMIN') or hasRole('FINANCIAL_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.financial.msgs.financial">
<form:form commandName="financialProfileForm" class="editForm" enctype="multipart/form-data">
	
	<h2 class="totalEquityDisplay">
		<fmt:message key="totalEquityLabel">
			<fmt:param><fmt:formatNumber value="${totalEquity}" type="currency" pattern="¤#,##0.00;-¤#,##0.00"/></fmt:param>
			<fmt:param><fmt:formatNumber value="${totalAssets}" type="currency" pattern="¤#,##0.00;-¤#,##0.00"/></fmt:param>
			<fmt:param><fmt:formatNumber value="${totalLiabilities}" type="currency" pattern="¤#,##0.00;-¤#,##0.00"/></fmt:param>
		</fmt:message>
	</h2>
	<fieldset>
	<table class="editTable">
			<tr>
				<th>
					<a class="actionMenuItem" id="assetsActionMenuLink" href="${pageContext.request.contextPath}/financial/financialAssetsActionMenu.html?offender=${offenderSummary.id}"></a>
				</th>
				<th><fmt:message key="assetCategoryLabel"/></th>
				<th><fmt:message key="assetDescriptionLabel"/></th>
				<th><fmt:message key="assetReportedDateLabel"/></th>
				<th><fmt:message key="assetAmountLabel"/></th>
			</tr>
		<tbody id="assetsBody">
			<c:forEach var="financialAssetItem" items="${financialProfileForm.financialAssetItems}" varStatus="status">
				<c:set var="financialAssetItem" value="${financialAssetItem}" scope="request"/>
				<c:set var="financialAssetItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${financialAssetItem.operation!=null}">
					<jsp:include page="/WEB-INF/views/financial/asset/includes/editTableRow.jsp"/>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	</fieldset>
	
	<fieldset>
	<table class="editTable">
			<tr>
				<th>
					<a class="actionMenuItem" id="liabilitiesActionMenuLink" href="${pageContext.request.contextPath}/financial/financialLiabilitiesActionMenu.html?offender=${offenderSummary.id}"></a>
				</th>
				<th><fmt:message key="liabilityCategoryLabel"/></th>
				<th><fmt:message key="liabilityDescriptionLabel"/></th>
				<th><fmt:message key="liabilityReportedDateLabel"/></th>
				<th><fmt:message key="liabilityAmountLabel"/></th>
			</tr>
		<tbody id="liabilitiesBody">
			<c:forEach var="financialLiabilityItem" items="${financialProfileForm.financialLiabilityItems}" varStatus="status">
				<c:set var="financialLiabilityItem" value="${financialLiabilityItem}" scope="request"/>
				<c:set var="financialLiabilityItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${financialLiabilityItem.operation!=null}">
					<jsp:include page="/WEB-INF/views/financial/liability/includes/editTableRow.jsp"/>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	</fieldset>
	
	<fieldset>
	<table class="editTable">
		<tr>
			<th>
				<a class="actionMenuItem" id="recurringDeductionActionMenuLink" href="${pageContext.request.contextPath}/financial/recurringDeductionActionMenu.html?offender=${offenderSummary.id}"></a>
			</th>
			<th><fmt:message key="deductionCategoryLabel"/></th>
			<th><fmt:message key="deductionDescriptionLabel"/></th>
			<th><fmt:message key="deductionReportedDateLabel"/></th>
			<th><fmt:message key="deductionAmountLabel"/></th>
			<th><fmt:message key="deductionFrequencyLabel"/></th>
		</tr>
		<tbody id="recurringDeductionBody">
			<c:forEach var="recurringDeductionItem" items="${financialProfileForm.recurringDeductionItems}" varStatus="status">
				<c:set var="recurringDeductionItem" value="${recurringDeductionItem}" scope="request"/>
				<c:set var="recurringDeductionItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${recurringDeductionItem.operation!=null}">
					<jsp:include page="/WEB-INF/views/financial/deduction/includes/editRecurringDeductionTableRow.jsp"/>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	</fieldset>
	
	<fieldset>
		<legend>
			<fmt:message key="financialDocumentAssociationsTitle"/>
		</legend>
		<span class="fieldGroup">
			<c:set var="financialDocumentAssociationItems" value="${financialProfileForm.financialDocumentAssociationItems}" scope="request"/>
			<jsp:include page="financialDocumentAssociationItems.jsp"/>
		</span>		
	</fieldset>

	<c:if test="${editFinancial}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
	
</form:form>
</fmt:bundle>