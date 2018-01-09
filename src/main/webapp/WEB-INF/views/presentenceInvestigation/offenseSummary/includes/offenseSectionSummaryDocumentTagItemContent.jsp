<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.offenseSectionSummary">
	<span id="offenseSectionSummaryAssociableDocument${offenseSectionSummaryAssociableDocumentItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="offenseSectionSummaryAssociableDocument${offenseSectionSummaryAssociableDocumentItemIndex}documentTagId${documentTagItemIndex}" name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="offenseSectionSummaryAssociableDocument${offenseSectionSummaryAssociableDocumentItemIndex}documentTagOperation${documentTagItemIndex}" name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="tagError"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeOffenseSectionSummaryAssociableDocument${offenseSectionSummaryAssociableDocumentItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/offenseSummary/removeDocumentTag.html?offenseSectionSummary=${offenseSectionSummary.id}"></a>
			<input type="text" id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>