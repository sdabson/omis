<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Dec 18, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:bundle basename="omis.trackeddocument.msgs.trackedDocument">
<c:if test="${trackedDocumentItem.operation.name eq 'REMOVE'}">
	<c:set var="trackedDocumentTableRowClass" value="removeRow"/>
</c:if>
<tr id="trackedDocumentItemTableRow[${trackedDocumentReceivalIndex}]" class="${trackedDocumentTableRowClass}">	
	<td>
		<a id="trackedDocumentItemRemoveLink[${trackedDocumentReceivalIndex}]" href="${pageContext.request.contextPath}/trackedDocument/removeTrackedDocument.html?itemIndex=${trackedDocumentReceivalIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="removeTrackedDocumentReceivalLink"/></span></a>
		<input id="trackedDocumentItemOperation[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].operation" type="hidden" value="${trackedDocumentReceivalItem.operation}"/>
		<input id="trackedDocumentItemReceivedByUserAccount[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedByUserAccount" type="hidden" value="${trackedDocumentReceivalItem.receivedByUserAccount.id}"/>
		<input id="trackedDocumentItemReceivial[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].trackedDocumentReceival" type="hidden" value="${trackedDocumentReceivalItem.trackedDocumentReceival.id}"/>
	</td>
	<c:choose>
		<c:when test="${trackedDocumentReceivalItem.operation.name eq 'EDIT' or trackedDocumentReceivalItem.operation.name eq 'REMOVE'}">
			<td>
				<c:out value="${trackedDocumentReceivalItem.category.name}"/>
			</td>
			<td>
				<c:out value="${trackedDocumentReceivalItem.receivedDate}"/>
			</td>
			<input id="trackedDocumentItemCategory[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].category" type="hidden" value="${trackedDocumentReceivalItem.category.id}"/>
			<input id="trackedDocumentItemReceivedDate[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" type="hidden" value="<fmt:formatDate value="${trackedDocumentReceivalItem.receivedDate}" pattern="MM/dd/yyyy"/>"/>
		</c:when>
		<c:otherwise>
			<td class="twoColumn">
				<select id="trackedDocumentReceivedCategory${trackedDocumentReceivalIndex}.category" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].category">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="trackedDocumentCategory" items="${categories}">
						<c:choose>
							<c:when test="${trackedDocumentReceivalItem.category eq trackedDocumentCategory}">
								<option value="${trackedDocumentCategory.id}" selected="selected"><c:out value="${trackedDocumentCategory.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${trackedDocumentCategory.id}"><c:out value="${trackedDocumentCategory.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].category" cssClass="error"/>
			</td>
			<td>
				<%-- <fmt:formatDate var="noteDate" value="${trackedDocumentReceivalItem.receivedDate}" pattern="MM/dd/yyyy"/>
				<input type="text" class="date" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" id="trackedDocumentItemsDate[${trackedDocumentReceivalIndex}]" value="${noteDate}"/>
				<form:errors path="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" cssClass="error"/> --%>
			
				<%-- <form:select path = "workAssignmentChangeReason">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${workAssignmentChangeReasons}" itemValue="id" itemLabel="name"/>
				</form:select>
				<form:errors path="workAssignmentChangeReason" cssClass="error"/> --%>
			
			
				<%-- <input name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" id="trackedDocumentItemsDate[${trackedDocumentReceivalIndex}]"
					class="date" value="${trackedDocumentReceivalItem.receivedDate}"/>
				<form:errors path="accommodationNotes[${noteIndex}].date" cssClass="error"/> --%>
				<fmt:formatDate value="${trackedDocumentReceivalItem.receivedDate}" type="date" var="formattedResultsDate"/>
				<input type="text" class="date" id="trackedDocumentItemsDate[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" value="${trackedDocumentReceivalItem.receivedDate}"/>
				<form:errors path="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" cssClass="error"/>
			</td>
		</c:otherwise>
	</c:choose>
	<td>
		<c:out value="${trackedDocumentReceivalItem.receivedByUserAccount.user.name.lastName}"/>, <c:out value="${trackedDocumentReceivalItem.receivedByUserAccount.user.name.firstName}"/> <c:if test="${trackedDocumentReceivalItem.receivedByUserAccount.user.name.middleName ne null}"><c:out value="${fn:substring(trackedDocumentReceivalItem.receivedByUserAccount.user.name.middleName, 0, 1)}."/></c:if> <c:out value="(${trackedDocumentReceivalItem.receivedByUserAccount.username})"/>
	</td>
</tr>
</fmt:bundle>