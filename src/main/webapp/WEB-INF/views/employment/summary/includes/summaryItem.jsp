<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.employment.msgs.summary">
<c:if test="${not empty employmentSummary}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="employmentLabel"/></span>
		<a href="${pageContext.request.contextPath}/employment/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><c:out value=" ${employmentSummary.employerName}" /></span>
		</a>
	</div>
</div>
</c:if>
</fmt:bundle>