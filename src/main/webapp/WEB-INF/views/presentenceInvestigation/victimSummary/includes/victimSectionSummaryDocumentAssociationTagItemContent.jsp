<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
	<span id="victimSectionSummaryDocumentAssociation${victimSectionSummaryDocumentAssociationItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="victimSectionSummaryDocumentAssociation${victimSectionSummaryDocumentAssociationItemIndex}documentTagId${documentTagItemIndex}" name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="victimSectionSummaryDocumentAssociation${victimSectionSummaryDocumentAssociationItemIndex}documentTagOperation${documentTagItemIndex}" name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeVictimSectionSummaryDocumentAssociation${victimSectionSummaryDocumentAssociationItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/removeDocumentTag.html?victimSectionSummary=${victimSectionSummary.id}"></a>
			<input type="text" id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>