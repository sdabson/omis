<%--
  - Action menu for financial liabilities.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.financial.msgs.financial">
<ul>
	<sec:authorize access="hasRole('FINANCIAL_LIABILITY_CREATE') or hasRole('ADMIN')">
		<li>
			<a id="createLiabilityLink" class="createLink" href="${pageContext.request.contextPath}/financial/createLiability.html"><span class="visibleLinkLabel"><fmt:message key="createLiabilityLink"/></span></a>
		</li>
	</sec:authorize>
</ul>
</fmt:bundle>