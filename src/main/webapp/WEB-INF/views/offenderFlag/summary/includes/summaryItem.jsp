<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.offender.msgs.offenderHeader">
<div class="offenderHeaderDetailsSection">
<c:forEach var="summary" items="${offenderFlagSummaries}">
	<c:if test="${summary.offenderFlagUsage eq 'REQUIREMENT'}">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><c:out value="${summary.offenderFlagCategoryName}" /></span>
			<a href="${pageContext.request.contextPath}/offenderFlag/edit.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><fmt:message key="${summary.offenderFlagValue eq 'true' ? 'offenderFlagTrueLabel' : summary.offenderFlagValue eq 'false' ? 'offenderFlagFalseLabel' : 'offenderFlagUnknownLabel'}"/></span>
			</a>
		</div>
	</c:if>
</c:forEach>
<!--<c:if test="${empty offenderFlagSummaries}" >
	<div class="headerCell">
		<span class="offenderHeaderFieldValue"><fmt:message key="offenderFlagNone"/></span>
	</div>
</c:if>-->
</div>
</fmt:bundle>
