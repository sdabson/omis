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
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<span id="earlyReleaseRequestDocumentAssociation${earlyReleaseRequestDocumentAssociationItemIndex}documentTagItem${documentTagItemIndex}" class="documentTag">
		<input type="hidden" id="earlyReleaseRequestDocumentAssociation${earlyReleaseRequestDocumentAssociationItemIndex}documentTagId${documentTagItemIndex}" name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTag" cssClass="error"/>
		<input type="hidden" id="earlyReleaseRequestDocumentAssociation${earlyReleaseRequestDocumentAssociationItemIndex}documentTagOperation${documentTagItemIndex}" name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTagOperation" value="${documentTagItem.documentTagOperation}"/>
		<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].documentTagOperation" cssClass="error"/>
		<span class="fieldGroup">
			<a class="removeLink removeTag" id="removeEarlyReleaseRequestDocumentAssociation${earlyReleaseRequestDocumentAssociationItemIndex}documentTagLink${documentTagItemIndex}"
			href="${pageContext.request.contextPath}/earlyReleaseTracking/removeDocumentTag.html"></a>
			<input type="text" id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" value="${documentTagItem.name}" />
		</span>
		<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].documentTagItems[${documentTagItemIndex}].name" cssClass="tagError"/>
	</span>
</fmt:bundle>