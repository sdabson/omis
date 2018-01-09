<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.residence.msgs.summary">
<c:if test="${not empty residenceSummary}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="residenceLabel"/></span>
		<a href="${pageContext.request.contextPath}/residence/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><c:out value=" ${residenceSummary.addressValue}" /><br/><c:out value="${residenceSummary.addressCityName}" /> <c:out value=", ${residenceSummary.addressStateAbbreviation}" /><c:out value=" ${residenceSummary.addressZipCodeValue}" /> </span>
		</a>
	</div>
</div>
</c:if>
</fmt:bundle>