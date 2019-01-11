<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>
<%-- 
 - Author: Ryan Johns
 - Author: Josh Divine
 - Version: 0.1.1 (Aug 9, 2018)
 - Since: OMIS 3.0
 --%>
 <%@ page trimDirectiveWhitespaces="true" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <fmt:setBundle basename="omis.msgs.common" var="common"/>
 <fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
 <fmt:bundle basename="omis.courtdocument.msgs.document">
 <form:form commandName="form" class="editForm" method="post" enctype="multipart/form-data">
 	<fieldset>
 		<legend><fmt:message key="documentGroupLabel"/></legend>
 		<span class="fieldGroup">
 			<form:label path="docket" class="fieldLabel">
 				<fmt:message key="docketLabel"/>
 			</form:label>
 			<select name="docket">
 				<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
 				<c:forEach items="${dockets}" var="docket">
	 				<option value="${docket.id}" ${docket == form.docket ? 'selected="selected"' : ''}>
						<fmt:message key="docketFormat">
	 						<fmt:param value="${docket.value}"/>
	 						<fmt:param value="${docket.court.name}"/>
 						</fmt:message>
 					</option>
 				</c:forEach>
 			</select>
 			<form:errors path="docket" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="courtDocumentCategory" class="fieldLabel">
 				<fmt:message key="categoryLabel"/>
 			</form:label>
 			<select name="courtDocumentCategory">
 				<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
 				<c:forEach items="${categories}" var="category">
 					<option value="${category.id}" ${category eq form.courtDocumentCategory ? 'selected="selected"' : ''}>
 						<c:out value="${category.name}"/>
 					</option>
 				</c:forEach>
 			</select>
 			<form:errors path="courtDocumentCategory" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="title" class="fieldLabel">
 				<fmt:message key="titleLabel"/>
 			</form:label>
 			<form:input path="title"/>
 			<form:errors path="title" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="date" class="fieldLabel">
 				<fmt:message key="dateLabel"/>
 			</form:label>
 			<form:input path="date" class="date"/>
 			<form:errors path="date" cssClass="error"/>
 		</span>
 		<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp"/>
 		<c:choose>
 		<c:when test="${not editing}">
 		<span class="fieldGroup">
 			<form:label path="data" class="fieldLabel">
 				<fmt:message key="dataLabel"/>
 			</form:label>
 			<input id="documentData" type="file" type="file" name="data">
 			<form:hidden path="fileExtension" id="dataFileExtension"/>
 			<form:errors path="data" cssClass="error"/>
 		</span>
 		</c:when>
 		<c:otherwise>
 			<span class="fieldGroup">
 				<form:label path="data" class="fieldLabel">
 					<fmt:message key="dataLabel"/>
 				</form:label>
 				<c:set var="filename" value = "${courtDocumentAssociation.document.filename}"/>
 				<a id="courtDocumentDownloadLink" href="${pageContext.request.contextPath}/documents/retrieveFile.html?document=${courtDocumentAssociation.document.id}" class="downloadLink"> 
 				<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
 					<fmt:formatDate value="${courtDocumentAssociation.document.date}" pattern="MM/dd/yyyy" var="documentDate"/>
 					<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
 				</fmt:message>
 				</a>
 			</span>
 		</c:otherwise>
 		</c:choose>
 	</fieldset>
 	
 	<c:if test="${not empty courtDocumentAssociation}">
	<c:set var="updatable" value="${courtDocumentAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${common}"/>"/>
	</p>
</form:form>
 </fmt:bundle>