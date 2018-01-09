<%--
  - Action menu for recurring deduction.
  -
  - Author: Yidong Li
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.financial.msgs.financial">
<ul>
	<sec:authorize access="hasRole('RECURRING_DEDUCTION_CREATE') or hasRole('ADMIN')">
		<li>
			<a id="createRecurringDeductionLink" class="createLink" href="${pageContext.request.contextPath}/financial/createRecurringDeduction.html"><span class="visibleLinkLabel"><fmt:message key="createRecurringDeductionLink"/></span></a>
		</li>
	</sec:authorize>
</ul>
</fmt:bundle>