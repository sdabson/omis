<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.probationterm.msgs.summary">
<c:if test="${not empty probationTermSummary}">
	<c:if test="${not empty probationTermSummary.expirationDate}">
	<div class="offenderHeaderDetailsSection">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><fmt:message key="probationDischargeDateLabel"/></span>
			<a href="${pageContext.request.contextPath}/probationTerm/list.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><fmt:formatDate value="${probationTermSummary.expirationDate}" pattern="MM/dd/yyyy"/></span>
			</a>
		</div>
	</div>
	</c:if>
</c:if>
</fmt:bundle>