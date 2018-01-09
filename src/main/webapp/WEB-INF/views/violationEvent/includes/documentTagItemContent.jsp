<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<span id="violationEventDocument${violationEventDocumentItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="violationEventDocument${violationEventDocumentItemIndex}documentTagId${documentTagItemIndex}" name="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="violationEventDocument${violationEventDocumentItemIndex}documentTagOperation${documentTagItemIndex}" name="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTagOperation" value="${documentTagItem.documentTagOperation}"/>
		<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTagOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeViolationEventDocument${violationEventDocumentItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/violationEvent/removeDocumentTag.html?violationEvent=${violationEvent.id}"></a>
			<input type="text" id="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" name="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
		</span>
		<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" cssClass="tagError"/>
	</span>
		
</fmt:bundle>