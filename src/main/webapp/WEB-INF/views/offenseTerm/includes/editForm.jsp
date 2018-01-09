<%--
  - Form to edit court cases with convictions and sentences as offense terms.
  -
  - Author: Stephen Abson
  - Author: Sheronda Vaughn
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<fmt:setBundle var="courtCaseBundle" basename="omis.courtcase.msgs.courtCase"/>
<form:form commandName="offenseTermForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="courtCaseLabel" bundle="${courtCaseBundle}"/></legend>
		<c:set var="courtCaseFields" value="${offenseTermForm.fields}" scope="request"/>
		<jsp:include page="/WEB-INF/views/courtCase/includes/courtCaseFields.jsp"/>
	</fieldset>
	<fieldset>
		<legend>
			<a href="${pageContext.request.contextPath}/offenseTerm/offensesActionMenu.html?person=${person.id}&amp;courtCase=${courtCase.id}" class="actionMenuItem" id="offensesActionMenuLink"></a>
			<fmt:message key="offensesLabel" bundle="${offenseTermBundle}"/>
		</legend>
		<div id="offensesContainer">		
			<c:choose>
				<c:when test="${not empty offenseTermForm.offenseItems}">
					<c:set var="offenseItems" value="${offenseTermForm.offenseItems}" scope="request"/>
					<c:forEach var="offenseItem" items="${offenseTermForm.offenseItems}" varStatus="status">
						<c:if test="${not empty offenseItem.operation}">
							<c:set var="offenseItem" value="${offenseItem}" scope="request"/>
							<c:set var="offenseItemIndex" value="${status.index}" scope="request"/>
							<jsp:include page="offenseItem.jsp"/>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p id="emptyOffensesMessage"><fmt:message key="emptyOffensesMessage" bundle="${offenseTermBundle}"/></p>
				</c:otherwise>
			</c:choose>				
		</div>
		<form:errors cssClass="error" path="offenseItems"/>
	</fieldset>
	<c:if test="${not empty courtCase}">
	<c:set var="updatable" value="${courtCase}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>