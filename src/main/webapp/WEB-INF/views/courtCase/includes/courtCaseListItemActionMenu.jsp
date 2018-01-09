<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Apr 19, 2016)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<ul>
	<c:if test="${not empty courtCase and not courtCase.flags.dismissed}">
		<li>
			<c:choose>
				<c:when test="${hasConvictions}">
					<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
						<a class="viewEditLink" href="${pageContext.request.contextPath}/offenseTerm/edit.html?courtCase=${courtCase.id}">
							<fmt:message key="viewEditOffenseLink"/>
						</a>
					</sec:authorize>
				</c:when>
				<c:otherwise>
					<sec:authorize access="hasRole('LIST_COURT_CASE') or hasRole('ADMIN')">
						<a class="viewEditLink" href="${pageContext.request.contextPath}/courtCase/edit.html?courtCase=${courtCase.id}">
							<fmt:message key="viewEditCourtCaseLink"/>
						</a>
					</sec:authorize>	
				</c:otherwise>
			</c:choose>
		</li>
		<sec:authorize access="hasRole('PROBATION_TERM_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty courtCase}">
				<li>
					<a class="listLink" href="${pageContext.request.contextPath}/probationTerm/list.html?courtCase=${courtCase.id}">
						<fmt:message key="viewProbationTermsLink"/>
					</a>
				</li>
			</c:if>
		</sec:authorize>
	</c:if>
	<sec:authorize access="hasRole('COURT_CASE_REMOVE') or hasRole('ADMIN')">
		<li>
			<a class="removeLink" href="${pageContext.request.contextPath}/courtCase/remove.html?courtCase=${courtCase.id}">
				<fmt:message key="removeDocketLink"/>
			</a>
		</li>
		<c:if test="${not empty courtCase and not courtCase.flags.dismissed }">
			<li>
				<a class="dismissDocketLink removeLink" href="${pageContext.request.contextPath}/courtCase/dismissDocket.html?courtCase=${courtCase.id}">
					<fmt:message key="dismissDocketLink"/>
				</a>
			</li>
		</c:if>
	</sec:authorize>
	<c:if test="${not empty courtCase and not courtCase.flags.dismissed }">
		<sec:authorize access="hasRole('LIST_COURT_CASE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/courtCase/courtCaseDetailsReport.html?courtCase=${courtCase.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="courtCaseDetailsReportLinkLabel"/></a>
			</li>
		</sec:authorize>
	</c:if>
</ul>
</fmt:bundle>