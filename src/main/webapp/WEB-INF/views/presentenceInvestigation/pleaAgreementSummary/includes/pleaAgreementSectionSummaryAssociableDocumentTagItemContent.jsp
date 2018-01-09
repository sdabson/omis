<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
	<span id="pleaAgreementSectionSummaryAssociableDocument${pleaAgreementSectionSummaryAssociableDocumentItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="pleaAgreementSectionSummaryAssociableDocument${pleaAgreementSectionSummaryAssociableDocumentItemIndex}documentTagId${documentTagItemIndex}" name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="pleaAgreementSectionSummaryAssociableDocument${pleaAgreementSectionSummaryAssociableDocumentItemIndex}documentTagOperation${documentTagItemIndex}" name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removePleaAgreementSectionSummaryAssociableDocument${pleaAgreementSectionSummaryAssociableDocumentItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/pleaAgreementSummary/removeDocumentTag.html?pleaAgreementSectionSummary=${pleaAgreementSectionSummary.id}"></a>
			<input type="text" id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>