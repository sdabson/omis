<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (February 24, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.caution.msgs.caution">
	<ul>
		<sec:authorize access="hasRole('CAUTION_CREATE') or hasRole('ADMIN')">
			<c:if test="${not empty offender}">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/caution/create.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="createCautionLink"/></span></a>
			</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('CAUTION_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty offender}">
			<li>
				<a href="${pageContext.request.contextPath}/caution/cautionListingReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="cautionListingReportLinkLabel"/></a>
			</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('CAUTION_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty caution}">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/caution/edit.html?caution=${caution.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditCautionLink"/></span></a>
			</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('CAUTION_REMOVE') or hasRole('ADMIN')">
			<c:if test="${not empty caution}">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/caution/remove.html?caution=${caution.id}"><span class="visibleLinkLabel"><fmt:message key="removeCautionLink"/></span></a>
			</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('CAUTION_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty caution}">
			<li>
				<a href="${pageContext.request.contextPath}/caution/cautionDetailsReport.html?caution=${caution.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="cautionDetailsReportLinkLabel"/></a>
			</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>