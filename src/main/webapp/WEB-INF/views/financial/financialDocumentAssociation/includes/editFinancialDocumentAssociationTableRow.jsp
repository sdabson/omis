<%--
 - Table row to edit financial document association.
 -
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:setBundle basename="omis.financial.msgs.financial"/>
	<span id="financialDocumentAssociationItem${financialDocumentAssociationItemIndex}" class="documentItem">
		<a class="removeLink" id="removeFinancialDocumentAssociationLink${financialDocumentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/financial/removeFinancialDocumentAssociation.html">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="financialDocumentAssociationId${financialDocumentAssociationItemIndex}"
				name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].financialDocumentAssociation"
				value="${financialDocumentAssociationItem.financialDocumentAssociation.id}"/>
		<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].financialDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="financialDocumentAssociationOperation${financialDocumentAssociationItemIndex}"
				name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].operation" value="${financialDocumentAssociationItem.operation}"/>
		<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].operation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].title"
					name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].title"
				value="${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].title}" />
			<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].date" class="fieldLabel">
				<fmt:message key="dateLabel"/>
			</label>
			<fmt:formatDate var="financialDocumentAssociationDate" value="${financialDocumentAssociationItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${financialDocumentAssociationDate}" id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].date"
					name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].date" class="date" />
			<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].date" cssClass="error"/>
		</span>
		
		<span class="fieldGroup tagLinkContainer">
			<a id="createDocumentTagItemLink${financialDocumentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${financialDocumentAssociationItemIndex}">
			<c:forEach var="documentTagItem" items="${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.documentTagOperation}">
					<jsp:include page="financialDocumentAssociationTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		<span class="fieldGroup">
			<label for="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty financialDocumentAssociationItem.document}">
					<c:set var="filename" value = "${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="financialDocumentAssociationDownloadLink${financialDocumentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/financial/financialDocumentAssociation/retrieveFile.html?document=${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].data"
						id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].data"
						value="${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].data}"/>
					<input type="hidden" name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].document"
						id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].document"
						value="${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].data" type="file"
								name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].fileExtension"
					id="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].fileExtension"
					value="${financialDocumentAssociationItems[financialDocumentAssociationItemIndex].fileExtension}"/>
			<form:errors path="financialDocumentAssociationItems[${financialDocumentAssociationItemIndex}].data" cssClass="error"/>
		</span>
	</span>