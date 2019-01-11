<%--
  - Summarizes victim.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<fmt:setBundle basename="omis.demographics.msgs.demographics" var="demographicsBundle"/>
<fmt:setBundle basename="omis.person.msgs.person" var="personBundle"/>
<div id="victimSummary" class="summaryHeader foreground">
	<p>
		<sec:authorize access="hasRole('ADMIN') or hasRole('VICTIM_PROFILE_VIEW')">
			<a class="victimProfileLink" href="${pageContext.request.contextPath}/victim/profile.html?victim=${victimSummary.id}" class="victimProfileLink" title="<fmt:message key='victimProfileLink' bundle='${victimBundle}'/>"><span class="linkLabel"><fmt:message key="victimProfileLink" bundle="${victimBundle}"/></span></a>
		</sec:authorize>
		<jsp:include page="../../contact/includes/contactSummary.jsp"/>
		<span class="contactSummarySection">
			<span class="contactSummarySubSection">
					<label class="subSectionLabel"><fmt:message key="lastContactDateLabel" bundle="${victimBundle}"/></label>
					<c:if test="${not empty victimSummary.lastContactDate}">
						<fmt:formatDate value="${victimSummary.lastContactDate}" pattern="MM/dd/yyyy" var="lastContactDate"/>
						<c:out value="${lastContactDate}"/>
					</c:if>
			</span>
		</span>
		<%-- <span class="headerLabel"><fmt:message key="victimNameLabel" bundle="${victimBundle}"/></span>
		<span class="headerValue">
			<c:out value="${victimSummary.lastName}"/>,
			<c:out value="${victimSummary.firstName}"/>
			<c:if test="${not empty victimSummary.middleName}">
				<c:out value="${fn:substring(victimSummary.middleName, 0, 1)}"/>
			</c:if>
			<c:if test="${not empty victimSummary.suffix}">
				<c:out value="${victimSummary.suffix}"/>
			</c:if>
			<c:if test="${victimSummary.offender}">
				#<c:out value="${victimSummary.offenderNumber}"/>
			</c:if>
		</span>
		<c:if test="${not empty victimSummary.sex}">
			<span class="headerLabel"><fmt:message key="sexLabel" bundle="${demographicsBundle}"/></span>
			<span class="headerValue"><fmt:message key="sexLabel.${victimSummary.sex.name}" bundle="${demographicsBundle}"/></span>
		</c:if>
		<c:if test="${not empty victimSummary.birthDate}">
			<span class="headerLabel"><fmt:message key="birthDateLabel" bundle="${personBundle}"/></span>
			<span class="headerValue"><fmt:formatDate value="${victimSummary.birthDate}" pattern="MM/dd/yyyy"/></span>
		</c:if> --%>
	</p>
</div>