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
 - Cautions list table body content.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.caution.msgs.caution">
<c:forEach var="caution" items="${cautions}">
	<c:choose>
		<c:when test="${omis:isDateRangeActive(caution.dateRange, currentDate)}">
			<c:set var="activeClass" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="activeClass" value="inactiveRecord"/>
		</c:otherwise>
	</c:choose>
	<tr class="${activeClass}">
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/caution/cautionsActionMenu.html?caution=${caution.id}"></a>
		</td>
		<td>
			<c:out value="${caution.description.name}"/>
		</td>
		<td>
			<c:out value="${caution.source.name}"/>
		</td>
		<td>
			<fmt:formatDate value="${caution.dateRange.startDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${caution.dateRange.endDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${caution.comment}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>