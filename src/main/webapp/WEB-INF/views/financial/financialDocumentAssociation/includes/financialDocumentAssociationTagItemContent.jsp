<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.financial.msgs.financial">
		<span class="fieldGroup documentTag" id="financialDocumentAssociation${financialDocumentAssociationItemIndex}documentTagItem${documentTagItemIndex}">
			<input type="hidden" id="financialDocumentAssociation${financialDocumentAssociationItemIndex}documentTagId${documentTagItemIndex}" name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
			<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
			<input type="hidden" id="financialDocumentAssociation${financialDocumentAssociationItemIndex}documentTagOperation${documentTagItemIndex}" name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTagOperation" value="${documentTagItem.documentTagOperation}"/>
			<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTagOperation" cssClass="error"/>
			
			<a class="removeLink removeTag" id="removeFinancialDocumentAssociation${financialDocumentAssociationItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/financial/removeDocumentTag.html?financial="></a>
			<input type="text" id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>	
</fmt:bundle>