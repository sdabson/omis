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
	</td>
	<c:choose>
		<c:when test="${trackedDocumentReceivalItem.operation.name eq 'EDIT'}">
			<td>
				<c:out value="${trackedDocumentReceivalItem.category.name}"/>
			</td>
			<td>
				<c:out value="${trackedDocumentReceivalItem.receivedDate}"/>
			</td>
		</c:when>
		<c:otherwise>
			<td class="twoColumn">
				<select id="trackedDocumentReceivedCategory${trackedDocumentReceivalIndex}.category" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].category">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="trackedDocumentCategory" items="${categories}">
						<option value="${trackedDocumentCategory.id}"><c:out value="${trackedDocumentCategory.name}"/></option>
					</c:forEach>
				</select>
				<form:errors path="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].category" cssClass="error"/>
			</td>
			<td>
				<fmt:formatDate value="${trackedDocumentReceivalItem.receivedDate}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
				<input type="text" class="date" id="trackedDocumentItemsDate[${trackedDocumentReceivalIndex}]" name="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" value="${trackedDocumentReceivalItem.receivedDate}"/>
				<form:errors path="trackedDocumentReceivalItems[${trackedDocumentReceivalIndex}].receivedDate" cssClass="error"/>
			</td>
		</c:otherwise>
	</c:choose>
	<td>
		<%-- <c:out value="${trackedDocumentReceivalItem.receivedByUserAccount.user.name.lastName}"/>, <c:out value="${trackedDocumentReceivalItem.receivedByUserAccount.user.name.firstName}"/> <c:out value="${fn:substring(trackedDocumentReceivalItem.receivedByUserAccount.user.name.middleName, 0, 1)}"/> <c:out value="${trackedDocumentReceivalItem.receivedByUserAccount.user.offenderNumber}"/> --%>
		<c:out value="${trackedDocumentReceivalItem.receivedByUserAccount.user.name.lastName}"/>
	</td>
</tr>
</fmt:bundle>