	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<a id="createViolationEventDocumentItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="violationEventDocumentItems">
	<c:forEach var="violationEventDocumentItem" items="${violationEventDocumentItems}" varStatus="status">
		<c:set var="violationEventDocumentItem" value="${violationEventDocumentItem}" scope="request"/>
		<c:set var="violationEventDocumentItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty violationEventDocumentItem.itemOperation}">
			<jsp:include page="violationEventDocumentItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>