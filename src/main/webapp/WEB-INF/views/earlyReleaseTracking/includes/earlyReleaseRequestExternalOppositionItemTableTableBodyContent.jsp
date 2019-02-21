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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="earlyReleaseRequestExternalOppositionItem" items="${earlyReleaseRequestExternalOppositionItems}" varStatus="status">
	<c:set var="earlyReleaseRequestExternalOppositionItem" value="${earlyReleaseRequestExternalOppositionItem}" scope="request"/>
	<c:set var="earlyReleaseRequestExternalOppositionItemIndex" value="${status.index}" scope="request"/>
	<c:set var="itemOperation" value="${earlyReleaseRequestExternalOppositionItem.itemOperation}" scope="request"/>
	<c:if test="${not empty earlyReleaseRequestExternalOppositionItem.itemOperation}">
		<jsp:include page="earlyReleaseRequestExternalOppositionItemTableRow.jsp"/>
	</c:if>
</c:forEach>