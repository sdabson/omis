<%--
 - Author: Yidong Li
 - Version: 0.1.1 (Dec 18, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.trackeddocument.msgs.trackedDocument">
<form:form commandName="trackedDocumentForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
		 <c:choose>
			<c:when test="${createFlag}">
				<form:label path="docket" class="fieldLabel">
					<fmt:message key="docketLabel"></fmt:message>
				</form:label>
				<form:select path="docket">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="optionDocket" items="${dockets}">
						<c:choose>
							<c:when test="${trackedDocumentForm.docket eq optionDocket}">
								<option value="${optionDocket.id}" selected="selected"><c:out value="${optionDocket.value}"/><c:out value="${optionDocket.court.location.address.value }"/>
							</c:when>
							<c:otherwise>
								<option value="${optionDocket.id}"><c:out value="${optionDocket.value}"/> <c:out value="${optionDocket.court.name}"/>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<form:errors path="docket" cssClass="error"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="existingDocketLabel"> </fmt:message><c:out value="${trackedDocumentForm.docket.value}"/> <c:out value="${trackedDocumentForm.docket.court.name}"/>
			</c:otherwise>
		</c:choose>
		</span>
	</fieldset>
	<table id="trackedDocumentEditTable" class="editTable">
		<thead>
			<tr>
				<th class="operation">
					<a class="actionMenuItem" id="trackedDocumentEditTableActionMenuLink" href="${pageContext.request.contextPath}/trackedDocumentManage/trackedDocumentEditTableActionMenu.html?offender=${offender.id}"></a>
				</th>			
				<th><fmt:message key="documentLabel"/></th>
				<th><fmt:message key="receivedDateLabel"/></th>
				<th><fmt:message key="receivedByLabel"/></th>
			</tr>
		</thead>
		<tbody id="trackedDocumentReceivalBody">
			<c:forEach var="trackedDocumentReceivalItem" items="${trackedDocumentForm.trackedDocumentReceivalItems}" varStatus="status">
				<c:set var="trackedDocumentReceivalItem" value="${trackedDocumentReceivalItem}" scope="request"/>
				<c:set var="trackedDocumentReceivalIndex" value="${status.index}" scope="request"/>
				<c:if test="${trackedDocumentReceivalItem.operation!=null}">
					<jsp:include page="/WEB-INF/views/trackedDocument/includes/createTrackedDocumentsTableRow.jsp"/>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<p class="buttons">
		<input id="saveButton" type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
	</p>
</form:form>
</fmt:bundle>