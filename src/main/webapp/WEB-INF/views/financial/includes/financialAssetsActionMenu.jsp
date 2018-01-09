<%--
  - Action menu for financial assets.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.financial.msgs.financial">
<ul>
	<sec:authorize access="hasRole('FINANCIAL_ASSET_CREATE') or hasRole('ADMIN')">
		<li>
			<a id="createAssetLink" class="createLink" href="${pageContext.request.contextPath}/financial/createAsset.html"><span class="visibleLinkLabel"><fmt:message key="createAssetLink"/></span></a>
		</li>
	</sec:authorize>
</ul>
</fmt:bundle>