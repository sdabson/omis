<%--
 - Displays all referrals, regardless of type.
 -
 - Author: Stephen Abson
 - 		   Joel Norris
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:bundle basename="omis.health.msgs.health">
<div id="openReferralList" class="referralList">
	<fmt:message key="openReferralTitle" var="openReferralTitle"/>
	<div class="titleHeader">
		<a href="${pageContext.request.contextPath}/health/referral/pending/actionMenu.html?facility=${facility.id}&amp;referralType=${referralType.name}&amp;offender=${offender.id}" id="open" class="actionMenuItem"></a>
		<c:out value="${openReferralTitle}"></c:out>
	</div>
	<div id="listContainer">
		<jsp:include page="/WEB-INF/views/health/request/includes/pendingListTable.jsp"/>
	</div>
</div>
<c:if test="${fn:length(pendingAuthorizations) gt 0 or referralType.name eq 'EXTERNAL_MEDICAL' or referralType.name eq 'ALL' or empty referralType}">
<div class="referralList">
	<c:choose>
		<c:when test="${not empty referralType}">
			<fmt:message key="pendingAuthorizationsTitle.${referralType.name}" var="pendingAuthorizationsTitle"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="pendingAuthorizationsTitle" var="pendingAuthorizationsTitle"/>
		</c:otherwise>
	</c:choose>
	<div class="titleHeader">
		<a href="${pageContext.request.contextPath}/health/referral/pendingAuthorization/actionMenu.html?facility=${facility.id}&amp;offender=${offender.id}&amp;referralType=${referralType.name}" id="pendingAuthorizations" class="actionMenuItem"></a>
		<c:out value="${pendingAuthorizationsTitle}"></c:out>
	</div>
	<div id="listContainer">
		<jsp:include page="/WEB-INF/views/health/referral/includes/pendingAuthorizationListTable.jsp"/> 
	</div>
</div>
</c:if>
<c:if test="${fn:length(authorizedReferrals) gt 0 or referralType.name eq 'EXTERNAL_MEDICAL' or referralType.name eq 'ALL' or empty referralType}">
<div class="referralList">
	<c:choose>
		<c:when test="${not empty referralType}">
			<fmt:message key="authorizedReferralTitle.${referralType.name}" var="authorizedReferralTitle"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="authorizedReferralTitle" var="authorizedReferralTitle"/>
		</c:otherwise>
	</c:choose>
	<div class="titleHeader">
		<a href="${pageContext.request.contextPath}/health/referral/authorized/actionMenu.html?facility=${facility.id}&amp;offender=${offender.id}&amp;referralType=${referralType.name}" id="authorized" class="actionMenuItem"></a>
		<c:out value="${authorizedReferralTitle}"></c:out>
	</div>
	<div id="listContainer">
		<jsp:include page="/WEB-INF/views/health/referral/includes/authorizedListTable.jsp"/> 
	</div>
</div>
</c:if>
<div class="referralList">
	<c:choose>
		<c:when test="${not empty referralType}">
			<fmt:message key="pendingReferralTitle.${referralType.name}" var="pendingReferralTitle"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="pendingReferralTitle" var="pendingReferralTitle"/>
		</c:otherwise>
	</c:choose>
	<div class="titleHeader">
		<a href="${pageContext.request.contextPath}/health/referral/scheduled/actionMenu.html?facility=${facility.id}&offender=${offender.id}&amp;referralType=${referralType.name}&amp;filterByStartDate=<fmt:formatDate value='${filterByStartDate}' pattern='MM/dd/yyyy'/>&amp;filterByEndDate=<fmt:formatDate value='${filterByEndDate}' pattern='MM/dd/yyyy'/>" id="pending" class="actionMenuItem"></a>
		<c:out value="${pendingReferralTitle}"></c:out>
	</div>
	<div id="listContainer">
		<jsp:include page="/WEB-INF/views/health/referral/includes/scheduledListTable.jsp"/> 
	</div>
</div>
<div class="referralList">
	<c:choose>
		<c:when test="${not empty referralType}">
			<fmt:message key="resolvedReferralTitle.${referralType.name}" var="resolvedReferralTitle"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="resolvedReferralTitle" var="resolvedReferralTitle"/>
		</c:otherwise>
	</c:choose>
	<div class="titleHeader">
		<a href="${pageContext.request.contextPath}/health/referral/resolved/actionMenu.html?facility=${facility.id}&offender=${offender.id}&amp;filterByStartDate=<fmt:formatDate value='${filterByStartDate}' pattern='MM/dd/yyyy'/>&amp;filterByEndDate=<fmt:formatDate value='${filterByEndDate}' pattern='MM/dd/yyyy'/>" id="resolved" class="actionMenuItem"></a>
		<c:out value="${resolvedReferralTitle}"></c:out>
	</div>
	<div id="listContainer">
		<jsp:include page="/WEB-INF/views/health/referral/includes/resolvedListTable.jsp"/>
	</div>
</div>
</fmt:bundle>