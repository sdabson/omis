<%--
  - Action menu for addresses.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.address.msgs.address">
<ul>
	<sec:authorize access="hasRole('ADDRESS_LIST') or hasRole('ADMIN')">
		<li><a class="listLink" href="${pageContext.request.contextPath}/address/list.html"><span class="visibleLinkLabel"><fmt:message key="listAddressesLink"/></span></a></li>
	</sec:authorize>
</ul>
</fmt:bundle>