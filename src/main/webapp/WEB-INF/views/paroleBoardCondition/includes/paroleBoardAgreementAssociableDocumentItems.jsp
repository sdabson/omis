	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
<a id="createAgreementAssociableDocumentItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="agreementAssociableDocumentItems">
	<c:forEach var="agreementAssociableDocumentItem" items="${agreementAssociableDocumentItems}" varStatus="status">
		<c:set var="agreementAssociableDocumentItem" value="${agreementAssociableDocumentItem}" scope="request"/>
		<c:set var="agreementAssociableDocumentItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty agreementAssociableDocumentItem.itemOperation}">
			<jsp:include page="paroleBoardAgreementAssociableDocumentItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>