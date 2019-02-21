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
  - Search results for offender relationships
  -
  - Author: Sheronda Vaughn
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="offenderRelationshipBundle" basename="omis.offenderrelationship.msgs.offenderRelationship"/>
<fmt:setBundle var="victimBundle" basename="omis.victim.msgs.victim"/>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
	<ul>
		<c:choose>
			<c:when test="${not empty offender and not empty relation}">
				<c:choose>
					<c:when test="${relationshipExist}">
						<c:choose>
							<c:when test="${relationIsOffender}">
								<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_PROFILE_VIEW')">
									<li><a class="moduleLink" title="<fmt:message key='viewOffenderProfileLink' bundle='${offenderBundle}'/>" href="${pageContext.request.contextPath}/offender/profile.html?offender=${relation.id}"><span class="visibleLinkLabel"><fmt:message key="viewOffenderProfileLink" bundle="${offenderBundle}"/></span></a></li>
								</sec:authorize>
							</c:when>
							<c:otherwise>
								<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_RELATIONSHIP_VIEW')">
									<li><a class="viewEditLink" href="${pageContext.request.contextPath}/offenderRelationship/update/edit.html?relationship=${relationship.id}" title="<fmt:message key='editOffenderRelationshipWithNewRelationLink' bundle='${offenderRelationshipBundle}'/>"><span class="visibleLinkLabel"><fmt:message key='editOffenderRelationshipWithNewRelationLink' bundle='${offenderRelationshipBundle}'/></span></a></li>
								</sec:authorize>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:if test="${offender ne relation}">
							<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_RELATIONSHIP_CREATE')">
								<li><a class="createLink" href="${pageContext.request.contextPath}/offenderRelationship/create.html?relation=${relation.id}&amp;offender=${offender.id}" title="<fmt:message key='createOffenderRelationshipLink' bundle='${offenderRelationshipBundle}'/>"><span class="visibleLinkLabel"><fmt:message key='createOffenderRelationshipLink' bundle='${offenderRelationshipBundle}'/></span></a></li>
							</sec:authorize>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty relation}">
					<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_RELATIONSHIP_LIST')">
						<c:choose>
							<c:when test="${relationIsOffender}">
								<li><a class="listLink" href="${pageContext.request.contextPath}/offenderRelationship/list.html?offender=${relation.id}"><fmt:message key="listRelationLabel" bundle="${offenderRelationshipBundle}"/></a></li>
							</c:when>
							<c:otherwise>
								<li><a class="listLink" href="${pageContext.request.contextPath}/offenderRelationship/list.html?relation=${relation.id}"><fmt:message key="listRelatedOffendersLink" bundle="${offenderRelationshipBundle}"/></a></li>
							</c:otherwise>
						</c:choose>
						<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_PROFILE_VIEW')">
							<li><a class="victimProfileLink" href="${pageContext.request.contextPath}/victim/profile.html?victim=${relation.id}" title="<fmt:message key='victimProfileLink' bundle='${victimBundle}'/>"><fmt:message key="victimProfileLink" bundle="${victimBundle}"/></a></li>
						</sec:authorize>
					</sec:authorize>
					<c:if test="${not relationIsOffender}">
						<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_CREATE')">
							<li><a class="createLink" href="${pageContext.request.contextPath}/offender/create.html?person=${relation.id}" title="<fmt:message key='createAsOffenderLink' bundle='${offenderRelationshipBundle}'/>"><fmt:message key="createAsOffenderLink" bundle="${offenderRelationshipBundle}"/></a></li>
						</sec:authorize>
					</c:if>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>