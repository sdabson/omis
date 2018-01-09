<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
	<span id="agreementAssociableDocument${agreementAssociableDocumentItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		<input type="hidden" id="agreementAssociableDocument${agreementAssociableDocumentItemIndex}documentTagId${documentTagItemIndex}" name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="agreementAssociableDocument${agreementAssociableDocumentItemIndex}documentTagOperation${documentTagItemIndex}" name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTagOperation" value="${documentTagItem.documentTagOperation}"/>
		<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTagOperation" cssClass="error"/>
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeAgreementAssociableDocument${agreementAssociableDocumentItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/paroleBoardCondition/removeDocumentTag.html?agreementAssociable=${agreementAssociable.id}"></a>
			<input type="text" id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
		</span>
		<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" cssClass="tagError"/>
	</span>
</fmt:bundle>