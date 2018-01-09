<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.victim.msgs.victim">
	<c:choose>
		<c:when test="${documentTagItem.documentTagOperation eq 'REMOVE'}">
			<c:set var="documentTagDisplayClass" value="documentTag removeContent"/>
		</c:when>
		<c:otherwise>
			<c:set var="documentTagDisplayClass" value="documentTag"/>
		</c:otherwise>
	</c:choose>
	<span  class="${documentTagDisplayClass}" id="victimDocument${victimDocumentItemIndex}documentTagItem${documentTagItemIndex}">
		<input type="hidden" id="victimDocument${victimDocumentItemIndex}documentTagId${documentTagItemIndex}" name="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="victimDocument${victimDocumentItemIndex}documentTagOperation${documentTagItemIndex}" name="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTagOperation" value="${documentTagItem.documentTagOperation}"/>
		<form:errors path="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].documentTagOperation" cssClass="error"/>
		
		<span class="fieldGroup documentTagFieldGroup">
			<a class="removeLink removeTagLink" id="removeVictimDocumentAssociation${victimDocumentItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/victim/document/removeDocumentTag.html?victim=${victim.id}"></a>
			<input type="text" id="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" name="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" class="documentTagInput" />
			<form:errors path="documentItems[${victimDocumentItemIndex}].tagItems[${documentTagItemIndex}].name" cssClass="tagError error"/>
		</span>
	</span>
</fmt:bundle>