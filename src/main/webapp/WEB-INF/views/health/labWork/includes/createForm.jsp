<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 - Author: Joel Norris
 - Version: 0.1.0 (Sept. 9, 2014)
 - Since: OMIS 3.0
--%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<form:form commandName="createLabWorksForm" class="editForm">
		<form:hidden path="offenderRequired"/>
		<c:if test="${createLabWorksForm.offenderRequired}">
			<span class="fieldGroup">
				<form:label path="offender" class="fieldLabel"><fmt:message key="offenderLabel"/></form:label>
					<c:choose>
						<c:when test="${not empty createLabWorksForm.offender}">
							<input id="offenderName" type="text" value="<c:out value='${createLabWorksForm.offender.name.lastName}'/>, <c:out value='${createLabWorksForm.offender.name.firstName}'/> <c:out value='${createLabWorksForm.offender.name.middleName}'/> (<c:out value='${createLabWorksForm.offender.offenderNumber}'/>)"/>
						</c:when>
						<c:otherwise>
							<input id="offenderName" type="text"/>
						</c:otherwise>
					</c:choose>
			</span>
		</c:if>
		<form:input id="offender" path="offender" type="hidden"/>
		<form:errors cssClass="error" path="offender"/>
		<c:set var="labWorkItems" value="${createLabWorksForm.labWorkItems}" scope="request"/>
		<jsp:include page="/WEB-INF/views/health/referral/labWork/includes/defaultSelections.jsp"/>
		<a href="${pageContext.request.contextPath}/health/labWork/addLabWorkItem.html" class="createLink" id="addLabWorkItemLink">
				<span class="visibleLinkLabel"><fmt:message key="createLabWorkLink"/></span></a>
		<jsp:include page="labWorkItemsContent.jsp"/>
		<form:errors cssClass="error" path="labWorkItems"/>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</form:form>
</fmt:bundle>