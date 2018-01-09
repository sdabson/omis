<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
	<span id="healthSectionSummaryDocumentAssociation${healthSectionSummaryDocumentAssociationItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		<input type="hidden" id="healthSectionSummaryDocumentAssociation${healthSectionSummaryDocumentAssociationItemIndex}documentTagId${documentTagItemIndex}" name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="healthSectionSummaryDocumentAssociation${healthSectionSummaryDocumentAssociationItemIndex}documentTagOperation${documentTagItemIndex}" name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeHealthSectionSummaryDocument${healthSectionSummaryDocumentAssociationItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/presentenceInvestigation/healthSummary/removeDocumentTag.html?healthSectionSummary=${healthSectionSummary.id}"></a>
			<input type="text" id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
	</span>
</fmt:bundle>