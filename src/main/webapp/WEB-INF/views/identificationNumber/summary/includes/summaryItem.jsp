<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offender.msgs.offenderHeader">
<div class="offenderHeaderDetailsSection">
<c:forEach var="summary" items="${identificationNumberSummaryItems}">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><c:out value="${summary.categoryName}" /></span>
		<a href="${pageContext.request.contextPath}/identificationNumber/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue">(<c:out value="${summary.issuerName}"/>) <c:out value="${summary.value}"/></span>
		</a>
	</div>
</c:forEach>
</div>
</fmt:bundle> 