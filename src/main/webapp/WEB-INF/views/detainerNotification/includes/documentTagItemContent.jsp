<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<span id="interstateDetainerActivity${interstateDetainerActivityItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		
		<input type="hidden" id="interstateDetainerActivity${interstateDetainerActivityItemIndex}documentTagId${documentTagItemIndex}" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="interstateDetainerActivity${interstateDetainerActivityItemIndex}documentTagOperation${documentTagItemIndex}" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" value="${documentTagItem.itemOperation}"/>
		<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].itemOperation" cssClass="error"/>
		
		
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeInterstateDetainerActivity${interstateDetainerActivityItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/detainerNotification/removeDocumentTag.html?interstateDetainerActivity=${interstateDetainerActivity.id}"></a>
			<input type="text" id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="error"/>
		</span>
		
	</span>
		
</fmt:bundle>