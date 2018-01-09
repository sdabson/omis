<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
	<span id="chemicalUseSectionSummaryDocumentAssociation${chemicalUseSectionSummaryDocumentAssociationItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="chemicalUseSectionSummaryDocumentAssociation${chemicalUseSectionSummaryDocumentAssociationItemIndex}documentTagId${documentTagItemIndex}" name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="chemicalUseSectionSummaryDocumentAssociation${chemicalUseSectionSummaryDocumentAssociationItemIndex}documentTagOperation${documentTagItemIndex}" name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeChemicalUseSectionSummaryDocumentAssociation${chemicalUseSectionSummaryDocumentAssociationItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/chemicalUseSummary/removeDocumentTag.html?chemicalUseSectionSummary=${chemicalUseSectionSummary.id}"></a>
			<input type="text" id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>