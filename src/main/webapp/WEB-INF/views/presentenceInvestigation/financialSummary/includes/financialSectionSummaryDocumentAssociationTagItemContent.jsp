<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSectionSummary">
	<span id="financialSectionSummaryDocumentAssociation${financialSectionSummaryDocumentAssociationItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="financialSectionSummaryDocumentAssociation${financialSectionSummaryDocumentAssociationItemIndex}documentTagId${documentTagItemIndex}" name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="financialSectionSummaryDocumentAssociation${financialSectionSummaryDocumentAssociationItemIndex}documentTagOperation${documentTagItemIndex}" name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeFinancialSectionSummaryDocumentAssociation${financialSectionSummaryDocumentAssociationItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/financialSummary/removeDocumentTag.html?financialSectionSummary=${financialSectionSummary.id}"></a>
			<input type="text" id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>