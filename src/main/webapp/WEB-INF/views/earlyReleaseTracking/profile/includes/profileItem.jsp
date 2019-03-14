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
 - Author: Annie Wahl
 - Version: 0.1.0 (Mar 8, 2019)
 - Since: OMIS 3.0
--%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/earlyReleaseTracking/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
					<c:when test="${not empty earlyReleaseRequestStatus}">
						<fmt:message key="earlyReleaseRequestStatusLabel">
							<fmt:param value="${earlyReleaseRequestStatus}"/>
						</fmt:message>
					</c:when>
					<c:otherwise>
						<fmt:message key="earlyReleaseRequestStatusEmptyLabel" />
					</c:otherwise>
				</c:choose>
			</span>
		</a>
	</div>
 </fmt:bundle>