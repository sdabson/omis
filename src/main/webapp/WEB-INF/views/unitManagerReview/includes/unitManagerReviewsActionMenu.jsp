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
 - Action menu for unit manager review listing screen.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 29, 2018)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.unitmanagerreview.msgs.unitManagerReview">
	<ul>
		<sec:authorize access="hasRole('UNIT_MANAGER_REVIEW_CREATE') or hasRole('ADMIN')">
			<c:if test="${not empty offender}">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/unitManagerReview/create.html?offender=${offender.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="createUnitManagerReviewLink"/></span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('UNIT_MANAGER_REVIEW_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty unitManagerReview}">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/unitManagerReview/edit.html?unitManagerReview=${unitManagerReview.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="viewEditUnitManagerReviewLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('UNIT_MANAGER_REVIEW_REMOVE') or hasRole('ADMIN')">
			<c:if test="${not empty unitManagerReview}">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/unitManagerReview/remove.html?unitManagerReview=${unitManagerReview.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="removeUnitManagerReviewLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>