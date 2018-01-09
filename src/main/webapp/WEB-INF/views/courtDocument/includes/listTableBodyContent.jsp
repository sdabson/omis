<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 8, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="documentBundle" basename="omis.document.msgs.document" />
<fmt:bundle basename="omis.courtdocument.msgs.document">
<c:forEach items="${courtCaseDocumentAssociationSummaries}" var="courtCaseDocumentAssociationSummary" varStatus="status">
	<tr>
		<td>
			<c:set var="mFilter" value="${filter}"/>
			<a href="${pageContext.request.contextPath}/courtCase/document/documentActionMenu.html?courtCaseDocumentAssociation=${courtCaseDocumentAssociationSummary.courtCaseDocumentAssociationId}&filter=${mFilter}" id="courtCaseDocument${status.index}" class="actionMenuItem"></a>
		</td>
		<td>
			<fmt:formatDate value="${courtCaseDocumentAssociationSummary.fileDate}" pattern="MM/dd/yyyy" var="fileDate"/>
			<c:out value="${fileDate}"/>
		</td>
		<td>
			<c:out value="${courtCaseDocumentAssociationSummary.docketName}"/>
		</td>
		<td>
			<c:out value="${courtCaseDocumentAssociationSummary.courtName}"/>
		</td>
		<td>
			<c:out value="${courtCaseDocumentAssociationSummary.categoryName}"/>
		</td>
		<td>
			<c:out value="${courtCaseDocumentAssociationSummary.documentTitle}"/>
		</td>
		<td>
			<fmt:message key="uploadDetailFormat" bundle="${documentBundle}">
				<c:choose>
					<c:when test="${not empty courtCaseDocumentAssociationSummary.updateUserMiddleName}">
						<c:set value="${courtCaseDocumentAssociationSummary.updateUserMiddleName}" var="middleName"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="middleName"/>
					</c:otherwise>
				</c:choose>
				<fmt:param value="${courtCaseDocumentAssociationSummary.updateUserFirstName}"/>
				<fmt:param value="${middleName}"/>
				<fmt:param value="${courtCaseDocumentAssociationSummary.updateUserLastName}"/>
				<fmt:formatDate value="${courtCaseDocumentAssociationSummary.updateDate}" pattern="MM/dd/yyyy" var="updateDate"/>
				<fmt:param value="${updateDate}"/>
			</fmt:message>
		</td>	
	</tr>
	</c:forEach>
</fmt:bundle>