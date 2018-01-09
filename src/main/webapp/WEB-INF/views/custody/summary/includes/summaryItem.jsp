<%-- Author: Sheronda Vaughn
   - Version: 0.1.0 (Jun 12, 2017)
   - Since: OMIS 3.0 
--%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.custody.msgs.summary">
<c:if test="${not empty custodyReviewSummary.custodyLevelName}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="custodyLevelNameLabel"/></span>
		<a href="${pageContext.request.contextPath}/custody/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><c:out value="${custodyReviewSummary.custodyLevelName}"/></span>
		</a>
	</div>
	<c:if test="${not empty custodyReviewSummary.actionDate}">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="actionDateLabel"/></span>
		<a href="${pageContext.request.contextPath}/custody/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><fmt:formatDate value="${custodyReviewSummary.actionDate}" pattern="MM/dd/YYYY"/></span>
		</a>
	</div>
	</c:if>	
</div>
</c:if>
</fmt:bundle>