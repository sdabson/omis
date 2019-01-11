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
							<li><a class="victimProfileLink" href="${pageContext.request.contextPath}/victim/profile.html?victim=${relation.id}" class="victimProfileLink" title="<fmt:message key='victimProfileLink' bundle='${victimBundle}'/>"><fmt:message key="victimProfileLink" bundle="${victimBundle}"/></a></li>
						</sec:authorize>
					</sec:authorize>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>