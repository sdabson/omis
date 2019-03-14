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
 - Action menu for response listing screen.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 29, 2018)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.response.msgs.response">
	<ul>
		<sec:authorize access="hasRole('RESPONSE_CREATE') or hasRole('ADMIN')">
			<c:if test="${empty response}">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/response/create.html">
						<span class="visibleLinkLabel">
							<fmt:message key="createResponseLink"/></span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('RESPONSE_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty response}">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/response/edit.html?response=${response.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="viewEditResponseLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('RESPONSE_REMOVE') or hasRole('ADMIN')">
			<c:if test="${not empty response}">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/response/remove.html?response=${response.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="removeResponseLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>