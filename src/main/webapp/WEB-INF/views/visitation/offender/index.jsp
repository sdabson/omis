<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<ul id="taskLinks" class="links taskLinks">
		<sec:authorize access="hasRole('VISITATION_ASSOCIATION_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/visitation/createVisitationAssociation.html?offender=${offender.id}">
					<fmt:message key="createVisitorLink"/>
				</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('VISITATION_LIST_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/visitation/list.html?offender=${offender.id}">
					<fmt:message key="viewVisitsListLink"/>
				</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('VISIT_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/visitation/createVisit.html?offender=${offender.id}">
					<fmt:message key="createVisitLink"/>
				</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('VISITATION_VISITORS') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/visitation/visitorLog.html?offender=${offender.id}">
					<fmt:message key="viewVisitorLogLink"/>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>