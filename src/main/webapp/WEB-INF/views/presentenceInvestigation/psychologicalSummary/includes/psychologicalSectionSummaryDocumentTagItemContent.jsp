<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
	<span id="psychologicalSectionSummaryDocument${psychologicalSectionSummaryDocumentItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="psychologicalSectionSummaryDocument${psychologicalSectionSummaryDocumentItemIndex}documentTagId${documentTagItemIndex}" name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="psychologicalSectionSummaryDocument${psychologicalSectionSummaryDocumentItemIndex}documentTagOperation${documentTagItemIndex}" name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removePsychologicalSectionSummaryDocument${psychologicalSectionSummaryDocumentItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/psychologicalSummary/removeDocumentTag.html?psychologicalSectionSummary=${psychologicalSectionSummary.id}"></a>
			<input type="text" id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>