<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<form:form commandName="victimDocumentsForm" class="editForm" enctype="multipart/form-data">
	<fieldset id="victimDocumentItemsContainer">
		<legend><fmt:message key="victimDocumentsLegend" bundle="${victimBundle}"/></legend>
		<c:forEach items="${victimDocumentsForm.documentItems}" var="item" varStatus="status">
			<c:set value="${status.index}" var="victimDocumentItemIndex" scope="request"/>
			<c:set value="${item}" var="victimDocumentItem" scope="request"/>
			<jsp:include page="victimDocumentItem.jsp"/>
		</c:forEach>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>